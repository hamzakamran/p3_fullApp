
#include <stdio.h>
#include <stdbool.h>

static inline bool check_input(int n){
    return 0 <= n && n <= 9;
}


int main (void){
    int x, y;
    printf("Enter the size of your array: ");
    scanf("%d %d", &x, &y);

    int a[x][y];
    int counts[10] = {0};

    for (int r = 0; r < x; ++r){
        printf("Enter row %d: ", r);
        for (int c = 0; c < y; ++c){
            int value;
            if(1 != scanf("%d", &value) || check_input(value) == false){
                printf("Values outside of range.\n");
                while(getchar() != '\n'){ //clears inputs;
                --r;
                break;
                }
            }
            a[r][c] = value;
        }
    }

    for (int r = 0; r < x; ++r){
        for (int c = 0; c < y; ++c){
            ++counts[a[r][c]];
        }
    }

    printf("\nTotal count for each digit:\n");
    for (int i = 0; i < 10; ++i){
        printf("Digit %d occurs %d time%s\n", i, counts[i], counts[i] > 1 ? "s": ""
    }

    

    return 0;
}
