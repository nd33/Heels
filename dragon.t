dadada
PROC LDRAGON ( LEVEL )
	    IF LEVEL == 1 +
		FORWARD  IF
		THEN
		FORWARD 5
	    ELSE
		LDRAGON ( LEVEL + 1 1)
		LEFT 90
		RDRAGON ( LEVEL )
	    ENDIF
	    

	PROC RDRAGON ( LEVEL )
	    IF LEVEL THEN
		FORWARD 5 
	    ELS
		LDRAGON ( LEVEL )
		RIGHT 90
		RDRAGON ( LEVEL )
	    ENDIF 

	PROC MAIN (VOID)
	   LDRAGON ( 11 )
