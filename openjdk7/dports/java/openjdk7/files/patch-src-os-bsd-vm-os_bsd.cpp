--- hotspot/src/os/bsd/vm/os_bsd.cpp.orig	2013-12-14 18:02:46.000000000 -0500
+++ hotspot/src/os/bsd/vm/os_bsd.cpp	2013-12-14 18:09:41.000000000 -0500
@@ -459,7 +459,13 @@
 
 #ifdef __APPLE__
 #define SYS_EXTENSIONS_DIR   "/Library/Java/Extensions"
-#define SYS_EXTENSIONS_DIRS  SYS_EXTENSIONS_DIR ":/Network" SYS_EXTENSIONS_DIR ":/System" SYS_EXTENSIONS_DIR ":/usr/lib/java"
+#define SYS_EXTENSIONS_DIRS  \
+	"@@PREFIX@@" SYS_EXTENSIONS_DIR \
+	":" SYS_EXTENSIONS_DIR \
+	":/Network" SYS_EXTENSIONS_DIR \
+	":/System" SYS_EXTENSIONS_DIR \
+	":@@PREFIX@@/lib/java" \
+	":/usr/lib/java"
         const char *user_home_dir = get_home();
         // the null in SYS_EXTENSIONS_DIRS counts for the size of the colon after user_home_dir
         int system_ext_size = strlen(user_home_dir) + sizeof(SYS_EXTENSIONS_DIR) +
