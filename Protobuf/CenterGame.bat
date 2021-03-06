
::协议文件路径, 最后不要跟“\”符号
set SOURCE_FOLDER=.\ProtoSrc\CenterGame
::如果文件中引用了别的proto文件，IMP_FOLDER是引用的proto文件的目录
::set IMP_FOLDER=.\ProtoSrc\Common
::编译器路径
set COMPILER_PATH=.\Protoc\bin\protoc.exe
::Java文件生成路径, 最后不要跟“\”符号
set JAVA_TARGET_PATH1=..\Center\proto
set JAVA_TARGET_PATH2=..\Game\proto
set CS_TARGET_PATH=..\TestCSProto

::删除之前创建的文件
del %JAVA_TARGET_PATH1%\*.* /f /s /q
del %JAVA_TARGET_PATH2%\*.* /f /s /q
del %CS_TARGET_PATH%\*.* /f /s /q
for /f "delims=" %%a in ('dir /ad/b/s %JAVA_TARGET_PATH1%') do (rd /q /s "%%a")
for /f "delims=" %%a in ('dir /ad/b/s %JAVA_TARGET_PATH2%') do (rd /q /s "%%a")
for /f "delims=" %%a in ('dir /ad/b/s %CS_TARGET_PATH%') do (rd /q /s "%%a")

::遍历所有文件
for /f "delims=" %%i in ('dir /b "%SOURCE_FOLDER%\*.proto"') do (
  %COMPILER_PATH% -I=%SOURCE_FOLDER%  --java_out=%JAVA_TARGET_PATH1% %SOURCE_FOLDER%\%%i
  %COMPILER_PATH% -I=%SOURCE_FOLDER%  --java_out=%JAVA_TARGET_PATH2% %SOURCE_FOLDER%\%%i
  %COMPILER_PATH% -I=%SOURCE_FOLDER%  --csharp_out=%CS_TARGET_PATH% %SOURCE_FOLDER%\%%i

)


echo 协议生成完毕。





::.\ProtoTool\bin\protoc.exe -I=./ProtoSrc --java_out=../Center/proto test.proto
pause