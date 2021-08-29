/**************Team******************/
/*1.THOMAS KONSTANTINIDIS [3160074]**/
/*2.ANGELIKI ALESTA [3130251]********/
/************************************/


library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.numeric_std.all;

ENTITY register_ID_EX IS
	generic(
		n: INTEGER := 16;
		addressSize : INTEGER := 3
	);
	port(
		clock, isEOR, wasJumpOut, isJump, isJR, isBranch, isR, isMFPC, isLW, isSW, isReadDigit, isPrintDigit : in std_logic; --AYTO EINAI KOMMENO STO PDF, AN EINAI LATHOS NA TO DW STO VIDEO.
		ALUFunc : in std_logic_vector(3 downto 0);
		R1Reg, R2Reg , immediate16 : in std_logic_vector(n-1 downto 0);
		R2AD, R1AD : in std_logic_vector(addressSize-1 downto 0);
		jumpShortAddr : in std_logic_vector(11 downto 0);
		--**--
		isEOR_IDEX, wasJumpOut_IDEX, isJump_IDEX, isJR_IDEX , isBranch_IDEX,
		isR_IDEX, isMFPC_IDEX, isLW_IDEX, isSW_IDEX, isReadDigit_IDEX, isPrintDigit_IDEX: OUT std_logic; --ta exei dyo fores aytos ta  isLW_IDEX, isSW_IDEX egw ta evala mia gt paizei n ekane lathos.
		ALUFunc_IDEX : out std_logic_vector(3 downto 0);
		R1Reg_IDEX, R2Reg_IDEX, immediate16_IDEX : out std_logic_vector(n-1 downto 0);
		R2AD_IDEX, R1AD_IDEX : out std_logic_vector(addressSize-1 downto 0);
		jumpShortAddr_IDEX : out std_logic_vector(11 downto 0)
	);
end register_ID_EX;


architecture behavior of register_ID_EX is
begin
pc: process(clock)
begin
	if clock = '1' THEN 
		isEOR_IDEX <= isEOR;
		wasJumpOut_IDEX <= wasJumpOut;
		isJump_IDEX <= isJump;
		isJR_IDEX <= isJR;
		isBranch_IDEX <= isBranch;
		isR_IDEX <= isR;
		isMFPC_IDEX <= isMFPC;
		ALUFunc_IDEX <= ALUFunc;
		R1Reg_IDEX <= R1Reg;
		R2Reg_IDEX <= R2Reg;
		immediate16_IDEX <= immediate16;
		R2AD_IDEX <= R2AD;
		R1AD_IDEX <= R1AD;
		jumpShortAddr_IDEX <= jumpShortAddr;
		isReadDigit_IDEX <= isReadDigit;
		isPrintDigit_IDEX <= isPrintDigit;
		isLW_IDEX <= isLW;
		isSW_IDEX <= isSW;
		
	end if;
end process pc;

end architecture behavior;
