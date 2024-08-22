#include "Reductions.h"

#include <algorithm>
#define DO_NOT_USE_MKL
#ifndef DO_NOT_USE_MKL
#include <mkl.h>
#endif

float Norm(const float (&x)[XDIM][YDIM][ZDIM])
{
    float result = 0.;
#ifdef DO_NOT_USE_MKL 
#pragma omp parallel for reduction(max:result)
    for (int i = 0; i < XDIM; i++)
    for (int j = 0; j < YDIM; j++)
    for (int k = 0; k < ZDIM; k++)
        result = std::max(result, std::abs(x[i][j][k]));
#else
    int totalElements = (XDIM) * (YDIM) * (ZDIM);
    int maxIndex = cblas_isamax(totalElements, &x[0][0][0], 1);
    result = std::abs(x[(maxIndex / ((YDIM)*(ZDIM)))]
                        [((maxIndex % ((YDIM)*(ZDIM))) / (ZDIM))]
                        [(maxIndex % (ZDIM))]);
#endif

    return result;
}

float InnerProduct(const float (&x)[XDIM][YDIM][ZDIM], const float (&y)[XDIM][YDIM][ZDIM])
{
    double result = 0.;

#ifdef DO_NOT_USE_MKL 
#pragma omp parallel for reduction(+:result)
    for (int i = 1; i < XDIM-1; i++)
    for (int j = 1; j < YDIM-1; j++)
    for (int k = 1; k < ZDIM-1; k++)
        result += (double) x[i][j][k] * (double) y[i][j][k];
#else
    int totalElements = (XDIM) * (YDIM) * (ZDIM);
    result = cblas_sdot(totalElements, &x[0][0][0], 1, &y[0][0][0], 1);
#endif

    return (float) result;
}
