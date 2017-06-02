def calc_num_swaps(hats):
    num_inversions = 0
    first_index = 0
    while first_index < len(hats):
        second_index = first_index + 1
        while second_index < len(hats):
            if hats[second_index] < hats[first_index]:
                num_inversions += 1
            second_index += 1
        first_index += 1
    print(num_inversions)


def get_array_from_file(arr_file):
    list_of_vals = arr_file.read()
    list_of_vals = list_of_vals.split()
    list_of_vals = list(map(int, list_of_vals))
    return list_of_vals


fileName = "HatFile.txt" # input("What file would you like to read from? ")
hatFile = open(fileName, "r")
hatArr = get_array_from_file(hatFile)
calc_num_swaps(hatArr)