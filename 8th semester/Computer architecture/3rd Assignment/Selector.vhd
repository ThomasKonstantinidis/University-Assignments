/**************Team******************/
/*1.THOMAS KONSTANTINIDIS [3160074]**/
/*2.ANGELIKI ALESTA [3130251]********/
/************************************/


library ieee;
use ieee.std_logic_1164.all;

entity Selector is
	generic ( n : INTEGER := 16 );
	
	port(
		Reg, Memory, WriteBack : in std_logic_vector (n-1 downto 0);
		operation : in std_logic_vector(1 downto 0);
		output : out std_logic_vector(n-1 downto 0)
	);
end entity Selector;

architecture behavior of Selector is
begin 
		with operation select
				output <= Reg when "00",
				WriteBack when "01",
				Memory when "10",
				"0000000000000000" when "11";

end architecture behavior;
