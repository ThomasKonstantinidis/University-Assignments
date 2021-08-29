/**************Team******************/
/*1.ANGELIKI ALESTA [3130251]********/
/*2.THOMAS KONSTANTINIDIS [3160074]**/
/************************************/


LIBRARY ieee;
Use ieee.STD_Logic_1164.all;

ENTITY signExtender IS
	generic (
		n : integer := 16;
		k : integer := 6
		);
		port (
			immediate : IN std_logic_vector (k-1 DOWNTO 0);
			extended : OUT std_logic_vector (n-1 DOWNTO 0)
		);
END signExtender;

Architecture Logicfunc of signExtender IS
begin
	extended <= (n-1 downto k => immediate(k-1)) & (immediate);
END Logicfunc;
