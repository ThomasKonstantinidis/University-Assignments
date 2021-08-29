/**************Team******************/
/*1.THOMAS KONSTANTINIDIS [3160074]**/
/*2.ANGELIKI ALESTA [3130251]********/
/************************************/


library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

ENTITY register_EX_MEM IS
	generic(
		n: INTEGER := 16;
		addressSize : INTEGER := 3
	);
	port(
		clock, isLW, WriteEnable, ReadDigit, PrintDigit : in std_logic; 
		R2Reg , Result : in std_logic_vector(n-1 downto 0);
		RegAD  : in std_logic_vector(addressSize-1 downto 0);
		--**--
		isLW_EXMEM, WriteEnable_EXMEM, ReadDigit_EXMEM, PrintDigit_EXMEM: OUT std_logic; 
		R2Reg_EXMEM, Result_EXMEM : out std_logic_vector(n-1 downto 0);
		RegAD_EXMEM : out std_logic_vector(addressSize-1 downto 0)
	);
end register_EX_MEM;


architecture behavior of register_EX_MEM is
begin
pc: process(clock)
begin
	if clock = '1' THEN 
		RegAD_EXMEM <= RegAD;
		R2Reg_EXMEM <= R2Reg;
		Result_EXMEM <= Result;
		isLW_EXMEM <= isLW;
		WriteEnable_EXMEM <= WriteEnable;
		ReadDigit_EXMEM <= ReadDigit;
		PrintDigit_EXMEM <= PrintDigit;
	end if;
end process pc;

end architecture behavior;
