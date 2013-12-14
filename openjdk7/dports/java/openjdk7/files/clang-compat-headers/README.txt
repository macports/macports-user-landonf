Mac OS X's Accelerate.framework assumes the availibility of immintrin.h
as supplied in clang and later releases of GCC. Since we have to build
using llvm-gcc-4.2, we supply a compatibility shim that allows successfully
building against the Accelerate framework.
