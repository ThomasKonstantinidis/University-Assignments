/**************Team******************/
/*1.ANGELIKI ALESTA [3130251]********/
/*2.THOMAS KONSTANTINIDIS [3160074]**/
/************************************/


library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

entity reg0 is 
	
	generic(
		n : INTEGER := 16
	);
	port( Input :IN STD_logic_Vector (n-1 DOWNTO 0);
			Enable, Clock :IN STD_logic;
			Output : OUT STD_logic_Vector(n-1 DOWNTO 0)
	);
end reg0;

architecture behavior of reg0 is
begin
	Output <= (OTHERS => '0');
end behavior;
				
