#pragma version(1)
#pragma rs java_package_name(com.example.renderscriptsample)
#pragma rs_fp_relaxed


uchar4 RS_KERNEL invert(uchar4 in, rs_kernel_context context, uint32_t x) {
  uchar4 out = in;

  uint32_t width = rsGetDimX(context);

  if(x > width/2.0f){
    out.r = clamp(in.r+50,0,255);
    out.g = clamp(in.g+50,0,255);
    out.b = clamp(in.b+50,0,255);
  }
  return out;
}


