#include "Laplacian.h"
#include "Parameters.h"
#include "PointwiseOps.h"
#include "Reductions.h"
#include "Utilities.h"
#include "Timer.h"

#include <iostream>


extern Timer timerTotal;
extern Timer timerLaplacianL2;
extern Timer timerSaxpyL2;
extern Timer timerNormL2;
extern Timer timerCopyL4;
extern Timer timerInnerProductL4;
extern Timer timerLaplacianL6;
extern Timer timerInnerProductL6;
extern Timer timerSaxpyL8;
extern Timer timerNormL8;
extern Timer timerSaxpyL9;
extern Timer timerCopyL13;
extern Timer timerInnerProductL13;
extern Timer timerSaxpyL16A;
extern Timer timerSaxpyL16B;
extern Timer timerInnerProductCopy;
extern Timer timerSaxpyNorm;






void ConjugateGradients(
    float (&x)[XDIM][YDIM][ZDIM],
    const float (&f)[XDIM][YDIM][ZDIM],
    float (&p)[XDIM][YDIM][ZDIM],
    float (&r)[XDIM][YDIM][ZDIM],
    float (&z)[XDIM][YDIM][ZDIM],
    const bool writeIterations)
{
    // Algorithm : Line 2
    timerLaplacianL2.Restart(); ComputeLaplacian(x, z); timerLaplacianL2.Pause();

    // timerSaxpyL2.Restart(); Saxpy(z, f, r, -1); timerSaxpyL2.Pause();
    // timerNormL2.Restart(); float nu = Norm(r); timerNormL2.Pause();
    timerSaxpyNorm.Restart(); float nu = SaxpyNorm(z,f,r, -1); timerSaxpyNorm.Pause();
    



    // Algorithm : Line 3
    if (nu < nuMax) return;
        
    // Algorithm : Line 4
    timerCopyL4.Restart(); Copy(r, p); timerCopyL4.Pause();

    timerInnerProductL4.Restart(); float rho=InnerProduct(p, r); timerInnerProductL4.Pause();

        
    // Beginning of loop from Line 5
    for(int k=0;;k++)
    {
        std::cout << "Residual norm (nu) after " << k << " iterations = " << nu << std::endl;

        // Algorithm : Line 6
        timerLaplacianL6.Restart(); ComputeLaplacian(p, z); timerLaplacianL6.Pause();
        timerInnerProductL6.Restart(); float sigma=InnerProduct(p, z); timerInnerProductL6.Pause();

        // Algorithm : Line 7
        float alpha=rho/sigma;

        // Algorithm : Line 8
        timerSaxpyL8.Restart(); Saxpy(z, r, r, -alpha); timerSaxpyL8.Pause();
        timerNormL8.Restart(); nu=Norm(r); timerNormL8.Pause();

        // Algorithm : Lines 9-12
        if (nu < nuMax || k == kMax) {
            timerSaxpyL9.Restart(); Saxpy(p, x, x, alpha); timerSaxpyL9.Pause();
            std::cout << "Conjugate Gradients terminated after " << k << " iterations; residual norm (nu) = " << nu << std::endl;
            if (writeIterations) WriteAsImage("x", x, k, 0, 127);
            return;
        }
            
        // Algorithm : Line 13
        // timerCopyL13.Restart(); Copy(r, z); timerCopyL13.Pause();
        // timerInnerProductL13.Restart(); float rho_new = InnerProduct(z, r); timerInnerProductL13.Pause();
        timerInnerProductCopy.Restart(); float rho_new = InnerProductCopy(r, z); timerInnerProductCopy.Pause();


        // Algorithm : Line 14
        float beta = rho_new/rho;

        // Algorithm : Line 15
        rho=rho_new;

        // Algorithm : Line 16
        timerSaxpyL16A.Restart(); Saxpy(p, x, x, alpha); timerSaxpyL16A.Pause();
        timerSaxpyL16B.Restart(); Saxpy(p, r, p, beta); timerSaxpyL16B.Pause();

        if (writeIterations) WriteAsImage("x", x, k, 0, 127);
    }
}
