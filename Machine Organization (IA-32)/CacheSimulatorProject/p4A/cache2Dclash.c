int arr2D[128][8];

int main(int argc, char *argv[]) {
	
	for (int x = 0; x < 100; x++) {
		for (int i = 0; i < 128; i += 64) {
			for (int j = 0; j < 8; ++j) {
				arr2D[i][j] = x + i + j;
			}	
		}
	}

	return 0;


}
