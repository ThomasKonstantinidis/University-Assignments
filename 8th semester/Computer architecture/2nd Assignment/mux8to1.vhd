/**************Team******************/
/*1.ANGELIKI ALESTA [3130251]********/
/*2.THOMAS KONSTANTINIDIS [3160074]**/
/************************************/


LIBRARY ieee;
Use ieee.STD_Logic_1164.all;
ENTITY mux8to1 IS
generic (
		n : integer :=16
	);
Port (Input0, Input1, Input2, Input3, Input4, Input5, Input6, Input7 :IN STD_LOGIC_VECTOR(n-1 DOWNTO 0);
		Choice :IN STD_LOGIC_VECTOR(2 DOWNTO 0);
		Output :OUT STD_LOGIC_VECTOR(n-1 DOWNTO 0)
		);
END mux8to1;

Architecture Logicfunc Of mux8to1 IS
begin
		with Choice select
		Output <= Input0 when "000",
			Input1 when "001",
			Input2 when "010",
			Input3 when "011",
			Input4 when "100",
			Input5 when "101",
			Input6 when "110",
			Input7 when "111",
			"0000000000000000" when OTHERS;
END Logicfunc;
			
