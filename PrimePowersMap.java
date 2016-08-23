//Matrix for the storing the prime power under an upper bound. Now this bound is 900.
//Grow it if needed, but in the same time add the new primpowers.
//This is needed for controls. If the new entries are not added, the 
//program wouldn't enable them. 

public class PrimePowersMap {

//Primepowers under N = 900

int primePowers[][];

public PrimePowersMap() {
  primePowers = new int[177][3]; //order, p, n
  primePowers[0][0] = 2;
  primePowers[0][1] = 2;
  primePowers[0][2] = 1;

  primePowers[1][0] = 3;
  primePowers[1][1] = 3;
  primePowers[1][2] = 1;
  
  primePowers[2][0] = 5;
  primePowers[2][1] = 5;
  primePowers[2][2] = 1;
  
  primePowers[3][0] = 7;
  primePowers[3][1] = 7;
  primePowers[3][2] = 1;

  primePowers[4][0] = 11;
  primePowers[4][1] = 11;
  primePowers[4][2] = 1;
  
  primePowers[4][0] = 13;
  primePowers[4][1] = 13;
  primePowers[4][2] = 1;
  
  primePowers[5][0] = 17;
  primePowers[5][1] = 17;
  primePowers[5][2] = 1;  

  primePowers[6][0] = 19;
  primePowers[6][1] = 19;
  primePowers[6][2] = 1;  
  
  primePowers[7][0] = 23;
  primePowers[7][1] = 23;
  primePowers[7][2] = 1;  
  
  primePowers[8][0] = 29;
  primePowers[8][1] = 29;
  primePowers[8][2] = 1;  
  
  primePowers[9][0] = 31;
  primePowers[9][1] = 31;
  primePowers[9][2] = 1;
  
  primePowers[10][0] = 37;
  primePowers[10][1] = 37;
  primePowers[10][2] = 1;
  
  primePowers[11][0] = 41;
  primePowers[11][1] = 41;
  primePowers[11][2] = 1;
  
  primePowers[12][0] = 43;
  primePowers[12][1] = 43;
  primePowers[12][2] = 1;
  
  primePowers[13][0] = 47;
  primePowers[13][1] = 47;
  primePowers[13][2] = 1;
  
  primePowers[14][0] = 53;
  primePowers[14][1] = 53;
  primePowers[14][2] = 1;
  
  primePowers[15][0] = 59;
  primePowers[15][1] = 59;
  primePowers[15][2] = 1;
  
  primePowers[16][0] = 61;
  primePowers[16][1] = 61;
  primePowers[16][2] = 1;
  
  primePowers[17][0] = 67;
  primePowers[17][1] = 67;
  primePowers[17][2] = 1;
  
  primePowers[18][0] = 71;
  primePowers[18][1] = 71;
  primePowers[18][2] = 1;
  
  primePowers[19][0] = 73;
  primePowers[19][1] = 73;
  primePowers[19][2] = 1;
  
  primePowers[20][0] = 79;
  primePowers[20][1] = 79;
  primePowers[20][2] = 1;
  
  primePowers[21][0] = 83;
  primePowers[21][1] = 83;
  primePowers[21][2] = 1;
  
  primePowers[22][0] = 89;
  primePowers[22][1] = 89;
  primePowers[22][2] = 1;
  
  primePowers[23][0] = 97;
  primePowers[23][1] = 97;
  primePowers[23][2] = 1;
  
  primePowers[24][0] = 101;
  primePowers[24][1] = 101;
  primePowers[24][2] = 1;
  
  primePowers[25][0] = 103;
  primePowers[25][1] = 103;
  primePowers[25][2] = 1;
  
  primePowers[26][0] = 107;
  primePowers[26][1] = 107;
  primePowers[26][2] = 1;
  
  primePowers[27][0] = 109;
  primePowers[27][1] = 109;
  primePowers[27][2] = 1;
  
  primePowers[28][0] = 113;
  primePowers[28][1] = 113;
  primePowers[28][2] = 1;
  
  primePowers[29][0] = 127;
  primePowers[29][1] = 127;
  primePowers[29][2] = 1;
  
  primePowers[30][0] = 131;
  primePowers[30][1] = 131;
  primePowers[30][2] = 1;
  
  primePowers[31][0] = 137;
  primePowers[31][1] = 137;
  primePowers[31][2] = 1;
  
  primePowers[32][0] = 139;
  primePowers[32][1] = 139;
  primePowers[32][2] = 1;
  
  primePowers[33][0] = 149;
  primePowers[33][1] = 149;
  primePowers[33][2] = 1;
  
  primePowers[34][0] = 151;
  primePowers[34][1] = 151;
  primePowers[34][2] = 1;
  
  primePowers[35][0] = 157;
  primePowers[35][1] = 157;
  primePowers[35][2] = 1;
  
  primePowers[36][0] = 163;
  primePowers[36][1] = 163;
  primePowers[36][2] = 1;
  
  primePowers[37][0] = 167;
  primePowers[37][1] = 167;
  primePowers[37][2] = 1;
  
  primePowers[38][0] = 173;
  primePowers[38][1] = 173;
  primePowers[38][2] = 1;
  
  primePowers[39][0] = 179;
  primePowers[39][1] = 179;
  primePowers[39][2] = 1;
  
  primePowers[40][0] = 181;
  primePowers[40][1] = 181;
  primePowers[40][2] = 1;
  
  primePowers[41][0] = 191;
  primePowers[41][1] = 191;
  primePowers[41][2] = 1;
  
  primePowers[42][0] = 193;
  primePowers[42][1] = 193;
  primePowers[42][2] = 1;
  
  primePowers[43][0] = 197;
  primePowers[43][1] = 197;
  primePowers[43][2] = 1;
  
  primePowers[44][0] = 199;
  primePowers[44][1] = 199;
  primePowers[44][2] = 1;
  
  primePowers[45][0] = 211;
  primePowers[45][1] = 211;
  primePowers[45][2] = 1;
  
  primePowers[46][0] = 223;
  primePowers[46][1] = 223;
  primePowers[46][2] = 1;
  
  primePowers[47][0] = 227;
  primePowers[47][1] = 227;
  primePowers[47][2] = 1;
  
  primePowers[48][0] = 229;
  primePowers[48][1] = 229;
  primePowers[48][2] = 1;
  
  primePowers[49][0] = 233;
  primePowers[49][1] = 233;
  primePowers[49][2] = 1;
  
  primePowers[50][0] = 239;
  primePowers[50][1] = 239;
  primePowers[50][2] = 1;
  
  primePowers[51][0] = 241;
  primePowers[51][1] = 241;
  primePowers[51][2] = 1;
  
  primePowers[52][0] = 251;
  primePowers[52][1] = 251;
  primePowers[52][2] = 1;
  
  primePowers[53][0] = 257;
  primePowers[53][1] = 257;
  primePowers[53][2] = 1;
  
  primePowers[54][0] = 263;
  primePowers[54][1] = 363;
  primePowers[54][2] = 1;
  
  primePowers[55][0] = 269;
  primePowers[55][1] = 269;
  primePowers[55][2] = 1;
  
  primePowers[56][0] = 271;
  primePowers[56][1] = 271;
  primePowers[56][2] = 1;
  
  primePowers[57][0] = 277;
  primePowers[57][1] = 277;
  primePowers[57][2] = 1;
  
  primePowers[58][0] = 281;
  primePowers[58][1] = 281;
  primePowers[58][2] = 1;
  
  primePowers[59][0] = 283;
  primePowers[59][1] = 283;
  primePowers[59][2] = 1;
  
  primePowers[60][0] = 293;
  primePowers[60][1] = 293;
  primePowers[60][2] = 1;
  
  primePowers[61][0] = 307;
  primePowers[61][1] = 307;
  primePowers[61][2] = 1;
  
  primePowers[62][0] = 311;
  primePowers[62][1] = 311;
  primePowers[62][2] = 1;
  
  primePowers[63][0] = 313;
  primePowers[63][1] = 313;
  primePowers[63][2] = 1;
  
  primePowers[64][0] = 317;
  primePowers[64][1] = 317;
  primePowers[64][2] = 1;
  
  primePowers[65][0] = 331;
  primePowers[65][1] = 331;
  primePowers[65][2] = 1;
  
  primePowers[66][0] = 337;
  primePowers[66][1] = 337;
  primePowers[66][2] = 1;
  
  primePowers[67][0] = 347;
  primePowers[67][1] = 347;
  primePowers[67][2] = 1;
  
  primePowers[68][0] = 349;
  primePowers[68][1] = 349;
  primePowers[68][2] = 1;
  
  primePowers[69][0] = 353;
  primePowers[69][1] = 353;
  primePowers[69][2] = 1;
  
  primePowers[70][0] = 359;
  primePowers[70][1] = 359;
  primePowers[70][2] = 1;
  
  primePowers[71][0] = 367;
  primePowers[71][1] = 367;
  primePowers[71][2] = 1;
  
  primePowers[72][0] = 373;
  primePowers[72][1] = 373;
  primePowers[72][2] = 1;
  
  primePowers[73][0] = 379;
  primePowers[73][1] = 379;
  primePowers[73][2] = 1;
  
  primePowers[74][0] = 383;
  primePowers[74][1] = 383;
  primePowers[74][2] = 1;
  
  primePowers[75][0] = 389;
  primePowers[75][1] = 389;
  primePowers[75][2] = 1;
  
  primePowers[76][0] = 397;
  primePowers[76][1] = 397;
  primePowers[76][2] = 1;
  
  primePowers[77][0] = 401;
  primePowers[77][1] = 401;
  primePowers[77][2] = 1;
  
  primePowers[78][0] = 409;
  primePowers[78][1] = 409;
  primePowers[78][2] = 1;
  
  primePowers[79][0] = 419;
  primePowers[79][1] = 419;
  primePowers[79][2] = 1;
  
  primePowers[80][0] = 421;
  primePowers[80][1] = 421;
  primePowers[80][2] = 1;
  
  primePowers[81][0] = 431;
  primePowers[81][1] = 431;
  primePowers[81][2] = 1;
  
  primePowers[82][0] = 433;
  primePowers[82][1] = 433;
  primePowers[82][2] = 1;
  
  primePowers[83][0] = 439;
  primePowers[83][1] = 439;
  primePowers[83][2] = 1;
  
  primePowers[84][0] = 443;
  primePowers[84][1] = 443;
  primePowers[84][2] = 1;
  
  primePowers[85][0] = 449;
  primePowers[85][1] = 449;
  primePowers[85][2] = 1;
  
  primePowers[86][0] = 457;
  primePowers[86][1] = 457;
  primePowers[86][2] = 1;
  
  primePowers[87][0] = 461;
  primePowers[87][1] = 461;
  primePowers[87][2] = 1;
  
  primePowers[88][0] = 463;
  primePowers[88][1] = 463;
  primePowers[88][2] = 1;
  
  primePowers[89][0] = 467;
  primePowers[89][1] = 467;
  primePowers[89][2] = 1;
  
  primePowers[90][0] = 479;
  primePowers[90][1] = 479;
  primePowers[90][2] = 1;
  
  primePowers[91][0] = 487;
  primePowers[91][1] = 487;
  primePowers[91][2] = 1;
  
  primePowers[92][0] = 491;
  primePowers[92][1] = 491;
  primePowers[92][2] = 1;
  
  primePowers[93][0] = 499;
  primePowers[93][1] = 499;
  primePowers[93][2] = 1;
  
  primePowers[94][0] = 503;
  primePowers[94][1] = 503;
  primePowers[94][2] = 1;
  
  primePowers[95][0] = 509;
  primePowers[95][1] = 509;
  primePowers[95][2] = 1;
  
  primePowers[96][0] = 521;
  primePowers[96][1] = 521;
  primePowers[96][2] = 1;
  
  primePowers[97][0] = 523;
  primePowers[97][1] = 523;
  primePowers[97][2] = 1;
  
  primePowers[98][0] = 541;
  primePowers[98][1] = 541;
  primePowers[98][2] = 1;
  
  primePowers[99][0] = 547;
  primePowers[99][1] = 547;
  primePowers[99][2] = 1;
  
  primePowers[100][0] = 557;
  primePowers[100][1] = 557;
  primePowers[100][2] = 1;
 
  primePowers[101][0] = 563;
  primePowers[101][1] = 563;
  primePowers[101][2] = 1;
  
  primePowers[102][0] = 569;
  primePowers[102][1] = 569;
  primePowers[102][2] = 1;
  
  primePowers[103][0] = 571;
  primePowers[103][1] = 571;
  primePowers[103][2] = 1;
  
  primePowers[104][0] = 577;
  primePowers[104][1] = 577;
  primePowers[104][2] = 1;
  
  primePowers[105][0] = 587;
  primePowers[105][1] = 587;
  primePowers[105][2] = 1;
  
  primePowers[106][0] = 593;
  primePowers[106][1] = 593;
  primePowers[106][2] = 1;
  
  primePowers[107][0] = 599;
  primePowers[107][1] = 599;
  primePowers[107][2] = 1;
  
  primePowers[108][0] = 601;
  primePowers[108][1] = 601;
  primePowers[108][2] = 1;
  
  primePowers[109][0] = 607;
  primePowers[109][1] = 607;
  primePowers[109][2] = 1;
  
  primePowers[110][0] = 607;
  primePowers[110][1] = 607;
  primePowers[110][2] = 1;
  
  primePowers[111][0] = 613;
  primePowers[111][1] = 613;
  primePowers[111][2] = 1;
  
  primePowers[112][0] = 617;
  primePowers[112][1] = 617;
  primePowers[112][2] = 1;
  
  primePowers[113][0] = 619;
  primePowers[113][1] = 619;
  primePowers[113][2] = 1;
  
  primePowers[114][0] = 631;
  primePowers[114][1] = 631;
  primePowers[114][2] = 1;
  
  primePowers[115][0] = 641;
  primePowers[115][1] = 641;
  primePowers[115][2] = 1;
  
  primePowers[116][0] = 643;
  primePowers[116][1] = 643;
  primePowers[116][2] = 1;
  
  primePowers[117][0] = 647;
  primePowers[117][1] = 647;
  primePowers[117][2] = 1;
  
  primePowers[118][0] = 653;
  primePowers[118][1] = 653;
  primePowers[118][2] = 1;
  
  primePowers[119][0] = 659;
  primePowers[119][1] = 659;
  primePowers[119][2] = 1;
  
  primePowers[120][0] = 661;
  primePowers[120][1] = 661;
  primePowers[120][2] = 1;
 
  primePowers[121][0] = 673;
  primePowers[121][1] = 673;
  primePowers[121][2] = 1;
  
  primePowers[122][0] = 677;
  primePowers[122][1] = 677;
  primePowers[122][2] = 1;
  
  primePowers[123][0] = 683;
  primePowers[123][1] = 683;
  primePowers[123][2] = 1;
  
  primePowers[124][0] = 691;
  primePowers[124][1] = 691;
  primePowers[124][2] = 1;
  
  primePowers[125][0] = 701;
  primePowers[125][1] = 701;
  primePowers[125][2] = 1;
  
  primePowers[126][0] = 709;
  primePowers[126][1] = 709;
  primePowers[126][2] = 1;
  
  primePowers[127][0] = 719;
  primePowers[127][1] = 719;
  primePowers[127][2] = 1;
  
  primePowers[128][0] = 727;
  primePowers[128][1] = 727;
  primePowers[128][2] = 1;
  
  primePowers[129][0] = 733;
  primePowers[129][1] = 733;
  primePowers[129][2] = 1;
  
  primePowers[130][0] = 739;
  primePowers[130][1] = 739;
  primePowers[130][2] = 1;
 
  primePowers[131][0] = 743;
  primePowers[131][1] = 743;
  primePowers[131][2] = 1;
  
  primePowers[132][0] = 751;
  primePowers[132][1] = 751;
  primePowers[132][2] = 1;
  
  primePowers[133][0] = 757;
  primePowers[133][1] = 757;
  primePowers[133][2] = 1;
  
  primePowers[134][0] = 761;
  primePowers[134][1] = 761;
  primePowers[134][2] = 1;
  
  primePowers[135][0] = 769;
  primePowers[135][1] = 769;
  primePowers[135][2] = 1;
  
  primePowers[136][0] = 773;
  primePowers[136][1] = 773;
  primePowers[136][2] = 1;
  
  primePowers[137][0] = 787;
  primePowers[137][1] = 787;
  primePowers[137][2] = 1;
  
  primePowers[138][0] = 797;
  primePowers[138][1] = 797;
  primePowers[138][2] = 1;
  
  primePowers[139][0] = 809;
  primePowers[139][1] = 809;
  primePowers[139][2] = 1;
  
  primePowers[140][0] = 811;
  primePowers[140][1] = 811;
  primePowers[140][2] = 1;
  
  primePowers[141][0] = 821;
  primePowers[141][1] = 821;
  primePowers[141][2] = 1;
  
  primePowers[142][0] = 823;
  primePowers[142][1] = 823;
  primePowers[142][2] = 1;
  
  primePowers[143][0] = 827;
  primePowers[143][1] = 827;
  primePowers[143][2] = 1;
  
  primePowers[144][0] = 829;
  primePowers[144][1] = 829;
  primePowers[144][2] = 1;
  
  primePowers[145][0] = 839;
  primePowers[145][1] = 839;
  primePowers[145][2] = 1;
  
  primePowers[146][0] = 853;
  primePowers[146][1] = 853;
  primePowers[146][2] = 1;
  
  primePowers[147][0] = 857;
  primePowers[147][1] = 857;
  primePowers[147][2] = 1;
  
  primePowers[148][0] = 857;
  primePowers[148][1] = 857;
  primePowers[148][2] = 1;
  
  primePowers[149][0] = 863;
  primePowers[149][1] = 863;
  primePowers[149][2] = 1;
  
  primePowers[150][0] = 877;
  primePowers[150][1] = 877;
  primePowers[150][2] = 1;
  
  primePowers[151][0] = 881;
  primePowers[151][1] = 881;
  primePowers[151][2] = 1;
  
  primePowers[152][0] = 883;
  primePowers[152][1] = 883;
  primePowers[152][2] = 1;
  
  primePowers[153][0] = 887;
  primePowers[153][1] = 887;
  primePowers[153][2] = 1;
  
  primePowers[154][0] = 4;
  primePowers[154][1] = 2;
  primePowers[154][2] = 2;
  
  primePowers[155][0] = 9;
  primePowers[155][1] = 3;
  primePowers[155][2] = 2;
  
  primePowers[156][0] = 25;
  primePowers[156][1] = 5;
  primePowers[156][2] = 2;
  
  primePowers[157][0] = 49;
  primePowers[157][1] = 7;
  primePowers[157][2] = 2;
  
  primePowers[158][0] = 121;
  primePowers[158][1] = 11;
  primePowers[158][2] = 2;
  
  primePowers[159][0] = 169;
  primePowers[159][1] = 13;
  primePowers[159][2] = 2;
  
  primePowers[160][0] = 289;
  primePowers[160][1] = 17;
  primePowers[160][2] = 2;
  
  primePowers[161][0] = 361;
  primePowers[161][1] = 19;
  primePowers[161][2] = 2;
  
  primePowers[162][0] = 529;
  primePowers[162][1] = 23;
  primePowers[162][2] = 2;
  
  primePowers[163][0] = 8;
  primePowers[163][1] = 2;
  primePowers[163][2] = 3;
  
  primePowers[164][0] = 27;
  primePowers[164][1] = 3;
  primePowers[164][2] = 3;
  
  primePowers[165][0] = 125;
  primePowers[165][1] = 5;
  primePowers[165][2] = 3;
  
  primePowers[166][0] = 343;
  primePowers[166][1] = 7;
  primePowers[166][2] = 3;
  
  primePowers[167][0] = 16;
  primePowers[167][1] = 2;
  primePowers[167][2] = 4;
  
  primePowers[168][0] = 81;
  primePowers[168][1] = 3;
  primePowers[168][2] = 4;
  
  primePowers[169][0] = 625;
  primePowers[169][1] = 5;
  primePowers[169][2] = 4;
  
  primePowers[170][0] = 32;
  primePowers[170][1] = 2;
  primePowers[170][2] = 5;
  
  primePowers[171][0] = 243;
  primePowers[171][1] = 3;
  primePowers[171][2] = 5;
  
  primePowers[172][0] = 64;
  primePowers[172][1] = 2;
  primePowers[172][2] = 6;
  
  primePowers[173][0] = 729;
  primePowers[173][1] = 3;
  primePowers[173][2] = 6;
  
  primePowers[174][0] = 128;
  primePowers[174][1] = 2;
  primePowers[174][2] = 7;
  
  primePowers[175][0] = 256;
  primePowers[175][1] = 2;
  primePowers[175][2] = 8;
  
  primePowers[176][0] = 512;
  primePowers[176][1] = 2;
  primePowers[176][2] = 9;
  
}

}