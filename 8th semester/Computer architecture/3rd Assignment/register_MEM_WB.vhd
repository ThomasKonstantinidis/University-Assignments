/**************Team******************/
/*1.THOMAS KONSTANTINIDIS [3160074]**/
/*2.ANGELIKI ALESTA [3130251]********/
/************************************/


library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

ENTITY register_MEM_WB IS
	generic(
		n: INTEGER := 16;
		addressSize : INTEGER := 3
	);
	port(
		Result : in std_logic_vector(n-1 downto 0);
		RegAD  : in std_logic_vector(addressSize-1 downto 0);
		clk : in std_logic;
		writeData: out std_logic_vector(n-1 downto 0);
		writeAD : out std_logic_vector(addressSize-1 downto 0)
	);
end register_MEM_WB;


architecture behavior of register_MEM_WB is
begin
pc: process(clk)
begin
	if clk = '1' THEN 
		writeData <= Result;
		writeAD <= RegAD;
	end if;
end process pc;

end architecture behavior;
