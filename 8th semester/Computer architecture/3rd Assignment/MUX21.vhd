/**************Team******************/
/*1.THOMAS KONSTANTINIDIS [3160074]**/
/*2.ANGELIKI ALESTA [3130251]********/
/************************************/


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
entity MUX21 is
    Port ( SEL : in  STD_LOGIC;
           DATAA   : in  STD_LOGIC_VECTOR (15 downto 0);
           DATAB   : in  STD_LOGIC_VECTOR (15 downto 0);
           OUT0   : out STD_LOGIC_VECTOR (15 downto 0));
end MUX21;
architecture Behavioral of MUX21 is
begin
    OUT0 <= DATAA when (SEL = '1') else DATAB;
end Behavioral;
