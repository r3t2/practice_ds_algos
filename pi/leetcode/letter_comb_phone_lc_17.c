#include <stdio.h>
#include <stdint.h>
#include <string.h>
#include <stdlib.h>

#define CHAR_TO_UINT(d) (d) - '0'

const char * string_table[] = 
{
	[0] = "",
	[1] = "",
	[2] = "abc",
	[3] = "def",
	[4] = "ghi",
	[5] = "jkl",
	[6] = "mno",
	[7] = "pqrs",
	[8] = "tuv",
	[9] = "wxyz",
};

uint8_t size_table[10];

void letter_combinations_rec(	char ** return_strings,
														 	const char * digits,
														 	const uint32_t num_digits,
														 	char * string_buffer,
														 	uint32_t * return_out_idx,
														 	uint32_t idx)
{
	if(idx == num_digits)
	{
		// printf("adding %s\n", string_buffer);
		return_strings[*return_out_idx] = calloc(num_digits+1, sizeof(char));
		strncpy(return_strings[*return_out_idx], string_buffer, num_digits);
		*return_out_idx += 1;
		return;
	}

	uint32_t d = CHAR_TO_UINT(digits[idx]);
	for(uint32_t i=0; i<size_table[d]; i++)
	{
		string_buffer[idx] = string_table[d][i];
		letter_combinations_rec(return_strings,
														digits,
														num_digits,
														string_buffer,
														return_out_idx,
														idx+1);
	}

}
char** letterCombinations(char* digits, int* return_size) {
	uint32_t num_digits = strlen(digits);
	if(num_digits == 0) return NULL;
	
	for(uint8_t i=0; i<10; i++)
	{
		size_table[i] = strlen(string_table[i]);
	}
	
	*return_size = 1;
	uint32_t d = 0;
	for(uint32_t i=0; i<num_digits; i++)
	{
		d = CHAR_TO_UINT(digits[i]);
		*return_size = (*return_size) * size_table[d];
	}

	char ** return_strings = malloc(*return_size * sizeof(char*));

	uint32_t return_out_idx = 0;
	char * string_buffer = calloc((num_digits+1)*sizeof(char), '\0');
	letter_combinations_rec(return_strings, digits, num_digits, string_buffer, &return_out_idx, 0);

	return return_strings;    
}

int main(void)
{
	int32_t return_size;
	char** res = letterCombinations("23", &return_size);
	printf("return_size = %u\n", return_size);
	for(uint32_t i=0; i<return_size; i++)
	{
		printf("%s, ", res[i]);
	}
	printf("\n");
	return 0;
}