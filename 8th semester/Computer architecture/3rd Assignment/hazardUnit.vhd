/**************Team******************/
/*1.THOMAS KONSTANTINIDIS [3160074]**/
/*2.ANGELIKI ALESTA [3130251]********/
/************************************/


library ieee;
use ieee.std_logic_1164.all;

entity hazardUnit is 
		port(
			isJR, isJump, wasJump, mustBranch : in std_logic;
			flush, wasJumpOut : out std_logic;
			JRopcode : out std_logic_vector(1 downto 0)
		);
end hazardUnit;

architecture behavior of  hazardUnit is
begin 
	--JRopcode:
	-- 00: +2
	--01: JumpADd
	--10: BranchAd

process(isJR, isJump, wasJump, mustBranch)
begin
	flush <= '0';
	
	if isJR = '1' OR isJump = '1' OR wasJump = '1' OR mustBranch = '1' then 
		flush <= '1';
	end if;
	
	if isJump = '1' OR isJR = '1' then JRopcode <= "01";
	
	elsif mustBranch = '1' then JRopcode <= "10";
	
	else JRopcode <= "00";
	
	end if;
end process;

	wasJumpOut <= isJump;

end architecture behavior;
