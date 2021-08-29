/**************Team******************/
/*1.ANGELIKI ALESTA [3130251]********/
/*2.THOMAS KONSTANTINIDIS [3160074]**/
/************************************/


library IEEE;
use IEEE.std_logic_1164.all;

entity reg is 
	port( Input, Clock, Enable : IN std_logic;
			Output: OUT std_logic);
end reg;

architecture Logicfunc of reg is
signal P1, P2, P3, P4, five, six, afterClock : std_logic;
begin
	P3 <= P1 NAND P4;
	P1 <= afterClock NAND P3;
	P2 <= NOT(afterClock AND P1 AND P4);
	P4 <= Input NAND P2;
	five<= six NAND P1;
	six <= P2 NAND five;
	afterClock <= Clock AND Enable;
	Output <= five;
end Logicfunc;
				
