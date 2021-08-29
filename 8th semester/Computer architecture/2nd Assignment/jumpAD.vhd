/**************Team******************/
/*1.ANGELIKI ALESTA [3130251]********/
/*2.THOMAS KONSTANTINIDIS [3160074]**/
/************************************/


LIBRARY ieee;
Use ieee.STD_Logic_1164.all;
Use ieee.numeric_std.all;

ENTITY jumpAD IS
generic (
		n : integer := 16;
		k : integer := 12
	);
	port (
		jumpADR :IN STD_Logic_Vector (k-1 DOWNTO 0);
		instrP2AD : IN STD_Logic_Vector (n-1 Downto 0);
		EjumpAD : OUT STD_Logic_Vector (n-1 DOWNTO 0)
	);
END jumpAD;

Architecture Logicfunc Of jumpAD IS
	signal extended, multed : STD_Logic_Vector (n-1 DOWNTO 0);
BEGIN
	extended <= (n-1 DOWNTO k=> jumpADR(k-1)) & (jumpADR);
	
process(instrP2AD)
BEGIN
	multed <= extended(n-2 DOWNTO 0) & '0';
	EjumpAD <= STD_Logic_Vector( unsigned(multed) + unsigned(instrP2AD));
END process;
END Logicfunc;
