set path=%1
cd C:\windows\System32
%path%\jboss-cli.bat --controller=localhost:10090 --connect command=:shutdown