/**************Team******************/
/*1.THOMAS KONSTANTINIDIS [3160074]**/
/*2.ANGELIKI ALESTA [3130251]********/
/************************************/


library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_arith.all;
use IEEE.STD_LOGIC_UNSIGNED.ALL;

entity Processor is
	port(
		KeyData: in std_logic_vector (15 DOWNTO 0);
		fromData: in std_logic_vector (15 DOWNTO 0);
		instr: in std_logic_vector (15 DOWNTO 0);
		clock1: in std_logic;
		clock2: in std_logic;
		printEnable, keyEnable, DataWriteFlag: out STD_LOGIC;
		dataAD: out std_logic_vector (15 DOWNTO 0);
		toData: out std_logic_vector (15 DOWNTO 0);
		printCode: out std_logic_vector (15 DOWNTO 0);
		printData: out std_logic_vector (15 DOWNTO 0);
		instructionAD: out std_logic_vector (15 DOWNTO 0);
		regOUT: out std_logic_vector (143 DOWNTO 0)
	);
end Processor;

architecture behavior of Processor is

	component trapUnit 
	 port(
			opcode : in std_logic_vector(3 downto 0);
			EOR : out std_logic
	 );
	end component;
	
	component MUX21 is
    Port ( SEL : in STD_LOGIC;
           DATAA   : in STD_LOGIC_VECTOR (15 downto 0);
           DATAB   : in STD_LOGIC_VECTOR (15 downto 0);
           OUT0   : out STD_LOGIC_VECTOR (15 downto 0));
	end component;
	
	component JRSelector 
	generic(
		n: INTEGER := 16		
	);
	 port(
		jumpAD, branchAd,PCP2AD : in std_logic_vector(n-1 downto 0);
		JRopcode : in std_logic_vector(1 downto 0);
		result : out std_logic_vector(n-1 downto 0)
	);
	end component;
	
	component reg16b
	port(
		Input : in std_logic_vector(15 downto 0);
		Enable, Clock : in std_logic;
		Output : out std_logic_vector(15 downto 0)
	);
	end component;
	
	component register_MEM_WB
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
	end component;
	
	component Selector
	generic ( n : INTEGER := 16 );
	port(
		Reg, Memory, WriteBack : in std_logic_vector (n-1 downto 0);
		operation : in std_logic_vector(1 downto 0);
		output : out std_logic_vector(n-1 downto 0)
	);
	end component;
	
	component register_IF_ID 
	generic(
		n: INTEGER := 16
	);
	port(
		inPC, inInstruction : IN std_logic_vector(n-1 downto 0);
		clock, IF_Flush, IF_ID_ENABLE : IN std_logic;
		--**--
		outPC,outInstruction : OUT std_logic_vector(n-1 downto 0)
	);
	end component;
	
	component Forwarder
	generic (addr_size : INTEGER := 3 );
	port(
		R1AD,R2AD,RegAD_EXMEM,RegAD_MEMWB : in std_logic_vector(addr_size-1 downto 0);
		S1,S2 : out std_logic_vector(1 downto 0)
	);
	end component;
	
	component AluControl
	port( opCode : in std_logic_vector(3 downto 0);
			func : in std_logic_vector(2 downto 0);
		   output : out std_logic_vector (3 downto 0) 
	);
	end component;
	
	component signExtender
	generic (
		n : integer := 16;
		k : integer := 6
		);
	port (
			immediate : IN std_logic_vector (k-1 DOWNTO 0);
			extended : OUT std_logic_vector (n-1 DOWNTO 0)
		);
	END component;
	
	component ALU
	GENERIC (
		n : INTEGER := 16
	);
	Port(Input1,Input2:IN std_logic_vector(n-1 DOWNTO 0);
	      Carryin: IN std_logic;
			CarryOut:OUT std_logic;
			Operation:IN std_logic_vector(3 DOWNTO 0);
			Output: OUT std_logic_vector(n-1 DOWNTO 0)
			);
	end component;
	
	component hazardUnit 
		port(
			isJR, isJump, wasJump, mustBranch : in std_logic;
			flush, wasJumpOut : out std_logic;
			JRopcode : out std_logic_vector(1 downto 0)
		);
	end component;
	
	component controller
	port(
		opCode : in std_logic_vector(3 downto 0);
		func: in std_logic_vector(2 downto 0);
		flush : in std_logic;
		isMPFC, isJumpD, isReadDigit, isPrintDigit, isR, isLW, isSW, isBranch, isJR : out std_logic
		);
	end component;
	
	component regFile
	generic(
		n : INTEGER := 16;
		k : INTEGER := 3;
		regNum : INTEGER := 8
	);
	port( Clock : in std_logic;
			Write1 : in std_logic_vector(n-1 downto 0);
			Write1AD,Read1AD,Read2AD :in std_logic_vector(k-1 downto 0);
			Read1,Read2 : out std_logic_vector(n-1 downto 0);
			OUTall : out std_logic_vector(n*regNum-1 downto 0)
	);
	end component;
	
	component register_ID_EX
	generic(
		n: INTEGER := 16;
		addressSize : INTEGER := 3
	);
	port(
		clock, isEOR, wasJumpOut, isJump, isJR, isBranch, isR, isMFPC, isLW, isSW, isReadDigit, isPrintDigit : in std_logic; 
		ALUFunc : in std_logic_vector(3 downto 0);
		R1Reg, R2Reg , immediate16 : in std_logic_vector(n-1 downto 0);
		R2AD, R1AD : in std_logic_vector(addressSize-1 downto 0);
		jumpShortAddr : in std_logic_vector(11 downto 0);
		--**--
		isEOR_IDEX, wasJumpOut_IDEX, isJump_IDEX, isJR_IDEX , isBranch_IDEX,
		isR_IDEX, isMFPC_IDEX, isLW_IDEX, isSW_IDEX, isReadDigit_IDEX, isPrintDigit_IDEX: OUT std_logic;
		ALUFunc_IDEX : out std_logic_vector(3 downto 0);
		R1Reg_IDEX, R2Reg_IDEX, immediate16_IDEX : out std_logic_vector(n-1 downto 0);
		R2AD_IDEX, R1AD_IDEX : out std_logic_vector(addressSize-1 downto 0);
		jumpShortAddr_IDEX : out std_logic_vector(11 downto 0)
	);
	end component;
	
	component register_EX_MEM
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
	end component;

	signal output1, OUT1,OUT2, OUT3, OUT0, outIFID_Instr, Output,ReadDigit_EXMEM, extended, OutPC, outInstruction, writeData, result, R1Reg_IDEX, R2Reg_IDEX, Immediate16_IDEX, Read1, Read2: std_logic_vector(15 DOWNTO 0);
	signal output2, ALUFunc_IDEX: std_logic_vector(3 DOWNTO 0);
	signal S1, S2, JRopcode: std_logic_vector(1 DOWNTO 0);
	signal JumpShortAddr_IDEX: std_logic_vector(15 DOWNTO 0);
	signal writeAD, R2AD_IDEX, R1AD_IDEX, RegAD_EXMEM: std_logic_vector(2 DOWNTO 0);
	signal EOR, IsBranch_IDEX, IsEOR_IDEX, IsLW_IDEX,  IsMFPC_IDEX,Result_EXMEM, outIstruction, IsPrintDigit_IDEX, wasJumpOut_IDEX, IsBranch, IsLW, IsR, IsSW, IsR_IDEX, IsPrintDigit, IsMFPC1, wasJumpOut, IsReadDigit, isJump_IDEX, isJR_IDEX , IsReadDigit_IDEX, IsSW_IDEX, IsLW_EXMEM, flush, CarryOut, IsJR1, IsJumpD, comp_2, comp_3, comp_1: std_logic;
	
	
	--
	signal opcode :STD_LOGIC_VECTOR(3 DOWNTO 0):= outIFID_Instr(15 DOWNTO 12);
	signal RD :STD_LOGIC_VECTOR(2 DOWNTO 0):= outIFID_Instr(11 DOWNTO 9);
	signal R1AD :STD_LOGIC_VECTOR(2 DOWNTO 0):= outIFID_Instr(8 DOWNTO 6);
	signal RT :STD_LOGIC_VECTOR(2 DOWNTO 0):= outIFID_Instr(5 DOWNTO 3);
	signal func :STD_LOGIC_VECTOR(2 DOWNTO 0):= outIFID_Instr(2 DOWNTO 0);
	signal immediate :STD_LOGIC_VECTOR(5 DOWNTO 0):= outIFID_Instr(5 DOWNTO 0);
	signal jumpShortAddr :STD_LOGIC_VECTOR(11 DOWNTO 0):= outIFID_Instr(11 DOWNTO 0);
	signal jumpShortAddr_IDEX1 :STD_LOGIC_VECTOR(11 DOWNTO 0);
	signal R2AD :STD_LOGIC_VECTOR(2 DOWNTO 0);
	signal ALUFunc :STD_LOGIC_VECTOR(3 DOWNTO 0);
	signal allRegOut :STD_LOGIC_VECTOR(127 DOWNTO 0);
	--
	

begin

	G0: hazardUnit 		port map (IsJR1, IsJumpD, comp_2 , '0', flush, wasJumpOut, JRopcode);
	G1: controller 		port map (opcode, func,comp_3,IsMFPC1, isJumpD, IsReadDigit, isPrintDigit, isR, isLW, isSW, isBranch, isJR1);
	G2: register_ID_EX 	port map (clock1 ,isBranch,EOR, IsJR1 , IsJumpD, IsLW, IsMFPC1,IsPrintDigit,IsR,IsReadDigit,IsSW, wasJumpOut,ALUFunc, Read1,Read2,extended,R2AD,R1AD, jumpShortAddr, isEOR_IDEX, wasJumpOut_IDEX, isJump_IDEX, isJR_IDEX , isBranch_IDEX, isR_IDEX, isMFPC_IDEX, isLW_IDEX, isSW_IDEX, isReadDigit_IDEX, isPrintDigit_IDEX,ALUFunc_IDEX,R1Reg_IDEX, R2Reg_IDEX, immediate16_IDEX,R2AD_IDEX, R1AD_IDEX,jumpShortAddr_IDEX1);
	G3: register_EX_MEM 	port map (isPrintDigit_IDEX,IsReadDigit_IDEX, IsSW_IDEX, clock1, IsLW_IDEX, R2Reg_IDEX, Output, R1AD_IDEX ,isLW_EXMEM, DataWriteFlag, keyEnable, printEnable, dataAD, toData, RegAD_EXMEM );
	G4: regFile 			port map (clock1, writeData, writeAD, R1AD, R2AD, Read1, Read2, allRegOut);
	G5: ALU 					port map (OUT3, OUT2 ,'0', CarryOut, ALUFunc_IDEX, Output);
	G6: Selector 			port map (R1Reg_IDEX, OUT1, R2Reg_IDEX, S1 ,output1);
	G7: Selector 			port map (R2Reg_IDEX, OUT1, writeData, S2 ,output1);
	G8: AluControl 		port map (opcode, func, output2);
	G9: forwarder 			port map (R1AD_IDEX, R2AD_IDEX, RegAD_EXMEM, writeAD, S1, S2);
   G11: signExtender 	port map (immediate,extended);
   G12: register_IF_ID 	port map (Output,instr,clock2,'0','1', outPC, outInstruction);
	G13: register_MEM_WB port map (Output, RegAD_EXMEM, clock1, writeData, writeAD);
	G14: trapUnit 			port map (opcode, EOR);
	G15: JRSelector 		port map (JumpShortAddr_IDEX, Immediate16_IDEX, OutPC, JRopcode, result);
	G16: reg16b 			port map (result, comp_1,  clock1, InstructionAD);
	G17: MUX21 				port map(IsMFPC_IDEX, output, Immediate16_IDEX , OUT3);
	G18: MUX21 				port map(IsMFPC_IDEX, outPC, output , OUT2);
	G19: MUX21 				port map(IsLW_EXMEM, outPC, fromData , OUT1);
	G20: MUX21 				port map( Result_EXMEM , keyData, ReadDigit_EXMEM, OUT0);
	
	
	selectRegister : process(isR) 
	begin
		case isR is
			when '1' =>
				R2AD <= RT;
			when '0' =>
				R2AD <= RD;
			when others =>
				R2AD <= RD;
		end case;
	end process;
	--
	comp_2 <= CarryOut AND IsBranch_IDEX;
	comp_3 <= flush OR IsEOR_IDEX;
	comp_1 <= NOT (EOR  OR IsEOR_IDEX);

end behavior;
	
