OBJ_DIR=.obj
BIN_DIR=exec
B_CMD=gprbuild
C_CMD=gprclean
RMFILE=rm -fr
EXEC=c c++ ada
OUT_DIRS = $(OBJ_DIR) $(BIN_DIR)

all: $(EXEC)

$(EXEC):
	@echo [==== Build projet $@ ====]
	@echo "[--> Debug]"
	$(B_CMD) -Xlang=$@
	@echo "[--> Release]"
	$(B_CMD) -Xlang=$@ -Xmode=release
	@echo ;

clean:
	@echo "[Clean projet c]"
	$(C_CMD) -Xlang=c
	$(C_CMD) -Xlang=c -Xmode=release
	@echo "[Clean projet c++]"
	$(C_CMD) -Xlang=c++
	$(C_CMD) -Xlang=c++ -Xmode=release
	@echo "[Clean projet ada]"
	$(C_CMD) -Xlang=ada
	$(C_CMD) -Xlang=ada -Xmode=release

mrproper : clean
	@echo "[Suppr. Obj & Exec Dir]"
	$(RMFILE) $(OUT_DIRS)