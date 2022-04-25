package com.tzj.javaffm;

import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.SymbolLookup;
import jdk.incubator.foreign.ValueLayout;

public class App {
  public static void main(String[] args) {
    callingCLibraryDemo();
    System.out.println();
    callingRustLibraryDemo();
  }

  private static void callingCLibraryDemo() {
    System.loadLibrary("rational_c");
    var linker = CLinker.systemCLinker();

    var rat_init =
        linker.downcallHandle(
            SymbolLookup.loaderLookup().lookup("rat_init").get(),
            FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));

    var rat_print =
        linker.downcallHandle(
            SymbolLookup.loaderLookup().lookup("rat_print").get(),
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));

    var rat_add =
        linker.downcallHandle(
            SymbolLookup.loaderLookup().lookup("rat_add").get(),
            FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS));

    var rat_sub =
        linker.downcallHandle(
            SymbolLookup.loaderLookup().lookup("rat_sub").get(),
            FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    var rat_mul =
        linker.downcallHandle(
            SymbolLookup.loaderLookup().lookup("rat_mul").get(),
            FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS));

    var rat_div =
        linker.downcallHandle(
            SymbolLookup.loaderLookup().lookup("rat_div").get(),
            FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS));

    var rat_free =
        linker.downcallHandle(
            SymbolLookup.loaderLookup().lookup("rat_free").get(),
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));

    try {
      var rat1 = rat_init.invoke(2, 4);
      var rat2 = rat_init.invoke(3, 9);

      var sum = rat_add.invoke(rat1, rat2);
      rat_print.invoke(sum);
      rat_free.invoke(sum);

      var diff = rat_sub.invoke(rat1, rat2);
      rat_print.invoke(diff);
      rat_free.invoke(diff);

      var prod = rat_mul.invoke(rat1, rat2);
      rat_print.invoke(prod);
      rat_free.invoke(prod);

      var quot = rat_div.invoke(rat1, rat2);
      rat_print.invoke(quot);
      rat_free.invoke(quot);

      rat_free.invoke(rat1);
      rat_free.invoke(rat2);

    } catch (Throwable err) {
      err.printStackTrace();
    }
  }

  private static void callingRustLibraryDemo() {
    System.loadLibrary("rational_rs");
    var linker = CLinker.systemCLinker();

    var rat_rs_init =
        linker.downcallHandle(
            SymbolLookup.loaderLookup().lookup("rat_rs_init").get(),
            FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.JAVA_INT, ValueLayout.JAVA_INT));

    var rat_rs_print =
        linker.downcallHandle(
            SymbolLookup.loaderLookup().lookup("rat_rs_print").get(),
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));

    var rat_rs_add =
        linker.downcallHandle(
            SymbolLookup.loaderLookup().lookup("rat_rs_add").get(),
            FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS));

    var rat_rs_sub =
        linker.downcallHandle(
            SymbolLookup.loaderLookup().lookup("rat_rs_sub").get(),
            FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS));
    var rat_rs_mul =
        linker.downcallHandle(
            SymbolLookup.loaderLookup().lookup("rat_rs_mul").get(),
            FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS));

    var rat_rs_div =
        linker.downcallHandle(
            SymbolLookup.loaderLookup().lookup("rat_rs_div").get(),
            FunctionDescriptor.of(ValueLayout.ADDRESS, ValueLayout.ADDRESS, ValueLayout.ADDRESS));

    var rat_rs_free =
        linker.downcallHandle(
            SymbolLookup.loaderLookup().lookup("rat_rs_free").get(),
            FunctionDescriptor.ofVoid(ValueLayout.ADDRESS));

    try {
      var rat_rs1 = rat_rs_init.invoke(2, 4);
      var rat_rs2 = rat_rs_init.invoke(3, 9);

      var sum = rat_rs_add.invoke(rat_rs1, rat_rs2);
      rat_rs_print.invoke(sum);
      rat_rs_free.invoke(sum);

      var diff = rat_rs_sub.invoke(rat_rs1, rat_rs2);
      rat_rs_print.invoke(diff);
      rat_rs_free.invoke(diff);

      var prod = rat_rs_mul.invoke(rat_rs1, rat_rs2);
      rat_rs_print.invoke(prod);
      rat_rs_free.invoke(prod);

      var quot = rat_rs_div.invoke(rat_rs1, rat_rs2);
      rat_rs_print.invoke(quot);
      rat_rs_free.invoke(quot);

      rat_rs_free.invoke(rat_rs1);
      rat_rs_free.invoke(rat_rs2);

    } catch (Throwable err) {
      err.printStackTrace();
    }
  }
}
