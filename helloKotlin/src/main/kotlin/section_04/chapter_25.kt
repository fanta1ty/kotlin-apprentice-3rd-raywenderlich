package section_04

/*
Key points:
- Kotlin/Native is used to compile Kotlin code to native binaries that can be run without virtual machines and Kotlin
runtimes.
- The name of the Kotlin/Native compiler is Konan. The command to run Konan is konanc.
- Kotlin/Native leverages the LLVM compiler to produce native binaries. Konan acts as a front-end of LLVM, producing an
Intermediate Representation for LLVM.
- The Kotlin/Native compiler can be installed from it's GitHub page at
"https://github.com/JetBrains/kotlin-native/releases"
- When installing the Kotlin/Native compiler, be sure to distinguish it from the kotlin-jvm compiler used to create
Java bytecode.
- Kotlin/Native code start with main function, similar to other C-like languages.
- The Kotlin standard library is statically linked to Kotlin/Native executables.
 */

fun main() {
}