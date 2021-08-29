/**************Team******************/
/*1.THOMAS KONSTANTINIDIS [3160074]**/
/*2.ANGELIKI ALESTA [3130251]********/
/************************************/


library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

ENTITY register_IF_ID IS
	generic(
		n: INTEGER := 16
	);
	port(
		inPC, inInstruction : IN std_logic_vector(n-1 downto 0);
		clock, IF_Flush, IF_ID_ENABLE : IN std_logic;
		--**--
		outPC,outInstruction : OUT std_logic_vector(n-1 downto 0)
	);
end register_IF_ID;


architecture behavior of register_IF_ID is
begin
pc: process(clock,IF_Flush,IF_ID_ENABLE)
begin
	if clock = '1' AND IF_ID_ENABLE = '1' THEN 
		outPC <= std_logic_vector( unsigned(inPC)+2);
		outInstruction <= inInstruction;
	elsif clock = '1' AND IF_Flush = '1' THEN 
		outPC <= (OTHERS => '0');
		outInstruction <= (OTHERS => '0');
	end if;
end process pc;

end architecture behavior;
