/*
Written by Hu Qin from Southeast University.
If you have any questions,you can contact 1013836586@qq.com
You can use this program for free.
*/


#include <stdio.h>

#define uint  unsigned int
#define uchar unsigned char

const uchar sbox[] = { 0x6, 0x5, 0xc, 0xa, 0x1, 0xe, 0x7, 0x9, 0xb, 0x0, 0x3, 0xd, 0x8, 0xf, 0x4, 0x2 };
const uchar RC[]   = { 0x01, 0x02, 0x04, 0x09, 0x12, 0x05, 0x0b, 0x16, 0x0c, 0x19, 0x13, 0x07, 0x0f, 0x1f, 0x1e, 0x1c, 0x18, 0x11, 0x03, 0x06, 0x0d, 0x1b, 0x17, 0x0e, 0x1d };

//counter
uint i = 0;

int main()
{
	
	uchar block[] = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };
	uchar key[] = { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00 };

	uchar temp1 = 0x00;
	uchar temp2 = 0x00;
	uchar temp3 = 0x00;
	uchar temp4 = 0x00;
	uchar temp5[] = { 0x00, 0x00 };

	/**********encryption**********/
	uint round = 0;
	do
	{
		//addroundkey
		i = 0;
		do
		{
			block[i] ^= key[i+2];
			i++;
		} while (i <= 7);

		//sbox
		i = 0;
		do
		{
			temp1    = sbox[(block[7] & 0x1) | ((block[5] & 0x1)) << 1 | ((block[3] & 0x1) << 2) | ((block[1] & 0x1) << 3)];
			block[7] = (block[7] >> 1) | (temp1 << 7);
			block[5] = (block[5] >> 1) | ((temp1 >> 1) << 7);
			block[3] = (block[3] >> 1) | ((temp1 >> 2) << 7);
			block[1] = (block[1] >> 1) | ((temp1 >> 3) << 7);
			temp1    = sbox[(block[6] & 0x1) | ((block[4] & 0x1)) << 1 | ((block[2] & 0x1) << 2) | ((block[0] & 0x1) << 3)];
			block[6] = (block[6] >> 1) | (temp1 << 7);
			block[4] = (block[4] >> 1) | ((temp1 >> 1) << 7);
			block[2] = (block[2] >> 1) | ((temp1 >> 2) << 7);
			block[0] = (block[0] >> 1) | ((temp1 >> 3) << 7);

			i++;
		} while (i <= 7);

		//player
		temp1    = block[5] >> 7;
		block[5] = (block[5] << 1) | (block[4] >> 7);
		block[4] = (block[4] << 1) | temp1;
		temp1    = block[3] << 4;
		block[3] = (block[3] >> 4) | (block[2] << 4);
		block[2] = (block[2] >> 4) | temp1;
		temp1    = block[1] << 5;
		block[1] = (block[1] >> 3) | (block[0] << 5);
		block[0] = (block[0] >> 3) | temp1;

		//keyextension
		temp1    = sbox[(key[9] & 0x1) | ((key[7] & 0x1) << 1) | ((key[5] & 0x1) << 2) | ((key[3] & 0x1) << 3)];
		temp2    = sbox[((key[9] >> 1) & 0x1) | (((key[7] >> 1) & 0x1) << 1) | (((key[5] >> 1) & 0x1) << 2) | (((key[3] >> 1) & 0x1) << 3)];
		temp3    = sbox[((key[9] >> 2) & 0x1) | (((key[7] >> 2) & 0x1) << 1) | (((key[5] >> 2) & 0x1) << 2) | (((key[3] >> 2) & 0x1) << 3)];
		temp4    = sbox[((key[9] >> 3) & 0x1) | (((key[7] >> 3) & 0x1) << 1) | (((key[5] >> 3) & 0x1) << 2) | (((key[3] >> 3) & 0x1) << 3)];
		key[9]   = (key[9] & 0xF0) | (temp1 & 0x1) | ((temp2 & 0x1) << 1) | ((temp3 & 0x1) << 2) | ((temp4 & 0x1) << 3);
		key[7]   = (key[7] & 0xF0) | ((temp1 >> 1) & 0x1) | (((temp2 >> 1) & 0x1) << 1) | (((temp3 >> 1) & 0x1) << 2) | (((temp4 >> 1) & 0x1) << 3);
		key[5]   = (key[5] & 0xF0) | ((temp1 >> 2) & 0x1) | (((temp2 >> 2) & 0x1) << 1) | (((temp3 >> 2) & 0x1) << 2) | (((temp4 >> 2) & 0x1) << 3);
		key[3]   = (key[3] & 0xF0) | ((temp1 >> 3) & 0x1) | (((temp2 >> 3) & 0x1) << 1) | (((temp3 >> 3) & 0x1) << 2) | (((temp4 >> 3) & 0x1) << 3);
		
		temp5[0] = key[8];
		temp5[1] = key[9];
		key[9]   = key[8] ^ key[7];
		key[8]   = temp5[1] ^ key[6];
		key[7]   = key[5];
		key[6]   = key[4];
		key[5]   = key[3];
		key[4]   = key[2];
		temp1    = key[3] << 4;
		key[3]   = ((key[3] >> 4) | (key[2] << 4)) ^ key[1];
		key[2]   = ((key[2] >> 4) | temp1) ^ key[0];
		key[1]   = temp5[1];
		key[0]   = temp5[0];
		
		key[9]   = (key[9] & 0xE0) | ((key[9] & 0x1F) ^ RC[round]);

		round++;
		
	} while (round < 25);

	i = 0;
	do
	{
		block[i] ^= key[i+2];
		i++;
	} while (i <= 7);
	
	for (i = 0; i < 8; i++)
	{
		printf("%02x", block[i]);
	}
	
	return 0;

}