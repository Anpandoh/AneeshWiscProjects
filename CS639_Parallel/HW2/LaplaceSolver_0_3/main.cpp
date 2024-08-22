#include "ConjugateGradients.h"
#include "Timer.h"
#include "Utilities.h"
#include <omp.h>


Timer timerTotal;
Timer timerLaplacianL2;
Timer timerSaxpyL2;
Timer timerNormL2;
Timer timerInnerProductL4;
Timer timerCopyL4;
Timer timerLaplacianL6;
Timer timerInnerProductL6;
Timer timerSaxpyL8;
Timer timerNormL8;
Timer timerSaxpyL9;
Timer timerCopyL13;
Timer timerInnerProductL13;
Timer timerSaxpyL16A;
Timer timerSaxpyL16B;
Timer timerInnerProductCopy;
Timer timerSaxpyNorm;



int main(int argc, char *argv[])
{
    std::cout << "Max number of omp threads = " << omp_get_max_threads() << std::endl;
    omp_set_num_threads(omp_get_max_threads());

    using array_t = float (&) [XDIM][YDIM][ZDIM];

    float *xRaw = new float [XDIM*YDIM*ZDIM];
    float *fRaw = new float [XDIM*YDIM*ZDIM];
    float *pRaw = new float [XDIM*YDIM*ZDIM];
    float *rRaw = new float [XDIM*YDIM*ZDIM];
    float *zRaw = new float [XDIM*YDIM*ZDIM];
    
    array_t x = reinterpret_cast<array_t>(*xRaw);
    array_t f = reinterpret_cast<array_t>(*fRaw);
    array_t p = reinterpret_cast<array_t>(*pRaw);
    array_t r = reinterpret_cast<array_t>(*rRaw);
    array_t z = reinterpret_cast<array_t>(*zRaw);
    
    // Initialization
    {
        Timer timer;
        timer.Start();
        InitializeProblem(x, f);
        timer.Stop("Initialization : ");
    }

    // Call Conjugate Gradients algorithm
    timerTotal.Reset();
    timerLaplacianL2.Reset();
    timerSaxpyL2.Reset();
    timerNormL2.Reset();
    timerInnerProductL4.Reset();
    timerCopyL4.Reset();
    timerLaplacianL6.Reset();
    timerInnerProductL6.Reset();
    timerSaxpyL8.Reset();
    timerNormL8.Reset();
    timerSaxpyL9.Reset();
    timerCopyL13.Reset();
    timerInnerProductL13.Reset();
    timerSaxpyL16A.Reset();
    timerSaxpyL16B.Reset();
    timerInnerProductCopy.Reset();
    timerSaxpyNorm.Reset();



    timerTotal.Restart();
    ConjugateGradients(x, f, p, r, z, false);
    timerTotal.Pause();

    timerLaplacianL2.Print("LaplacianL2 time: ");
    timerSaxpyNorm.Print("NEW SaxpyNorm Time: ");
    // timerSaxpyL2.Print("SaxpyL2 time: "); 
    // timerNormL2.Print("NormL2 time: "); 
    timerCopyL4.Print("CopyL4 time: "); 
    timerInnerProductL4.Print("InnerProductL4 time: "); 

    timerLaplacianL6.Print("LaplacianL6 Time: ");
    timerInnerProductL6.Print("InnerProductL6 Time: ");
    timerSaxpyL8.Print("SaxpyL8 Time: ");
    timerNormL8.Print("NormL8 Time: ");
    timerSaxpyL9.Print("SaxpyL9 Time: ");
    // timerCopyL13.Print("CopyL13 Time: ");
    // timerInnerProductL13.Print("InnerProductL13 Time: ");
    timerInnerProductCopy.Print("NEW InnerProductCopy Time: ");
    timerSaxpyL16A.Print("SaxpyL16A Time: ");
    timerSaxpyL16B.Print("SaxpyL16B Time: ");
    timerTotal.Print("Total Time: ");

    return 0;
}
