/**************Team******************/
/*1.THOMAS KONSTANTINIDIS [3160074]**/
/*2.ANGELIKI ALESTA [3130251]********/
/************************************/


library ieee;
use ieee.std_logic_1164.all;

entity trapUnit is
	 port(
			opcode : in std_logic_vector(3 downto 0);
			EOR : out std_logic
	 );
end trapUnit;

architecture behavior of trapUnit is
begin	
	PROCESS(opcode)
	begin 
		 if opcode = "1110" then 
			EOR <= '1';
		 else 
		   EOR <= '0';
		end if;
	end process;
end architecture behavior;
