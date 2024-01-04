export GOARCH=arm
export GOOS=android
export CGO_ENABLED=1
export CC=/mnt/android-ndk-r21b/toolchains/llvm/prebuilt/linux-x86_64/bin/armv7a-linux-androideabi21-clang
go build -buildmode=c-shared -o output/android/armeabi-v7a/libadd.so ./*.go

echo "Build armeabi-v7a success"

export GOARCH=arm64
export GOOS=android
export CGO_ENABLED=1
export CC=/mnt/android-ndk-r21b/toolchains/llvm/prebuilt/linux-x86_64/bin/aarch64-linux-android21-clang
go build -buildmode=c-shared -o output/android/arm64-v8a/libadd.so ./*.go

echo "Build arm64-v8a success"

