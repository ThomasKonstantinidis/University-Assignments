/**************Team******************/
/*1.THOMAS KONSTANTINIDIS [3160074]**/
/*2.ANGELIKI ALESTA [3130251]********/
/************************************/


library IEEE;
use IEEE.std_logic_1164.all;


ENTITY reg16b IS -- set as TOP LEVEL ENTITY

	port(
		Input : in std_logic_vector(15 downto 0);
		Enable, Clock : in std_logic;
		Output : out std_logic_vector(15 downto 0)
	);
end reg16b;


architecture behavior of reg16b is
begin
process(Clock)
begin
	if clock 'event AND Clock = '0' THEN
		IF Enable = '1' THEN
			Output <= Input;
		end if;
	end if;
end process;

end behavior;
