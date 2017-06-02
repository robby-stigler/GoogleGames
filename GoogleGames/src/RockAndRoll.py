def create_map(map_name):
    map_file = open(map_name, "r")
    map_list = map_file.read().splitlines()
    row_index = 0
    while row_index < len(map_list):
        row_list = map_list[row_index].split(sep=" ")
        row_list = list(map(int, row_list))
        map_list[row_index] = row_list
        row_index += 1
    print("The following map was constructed from the given file: ")
    for row in map_list:
        for col in row:
            print(col, end=" ")
        print()
    print("With dimensions " + len(map_list).__str__() + " x " + len(map_list[0]).__str__())
    return map_list


def create_movement_list(move_name):
    move_file = open(move_name, "r")
    move_file = move_file.read().split()
    print("The following movements were constructed from the given file: ")
    for move in move_file:
        print(move, end=" ")
    print()
    return move_file


def find_sub_position(map_list, move_list):
    start_row = 0
    while start_row < len(map_list):
        start_col = 0
        while start_col < len(map_list[0]):
            curr_row = start_row
            curr_col = start_col
            move_index = 0
            valid_move = True
            while move_index < len(move_list) and valid_move:
                move = move_list[move_index]
                if move == "N":
                    curr_row -= 1
                elif move == "S":
                    curr_row += 1
                elif move == "E":
                    curr_col += 1
                elif move == "W":
                    curr_col -= 1
                else:
                    print("You done messed up A-aron")
                    return
                if curr_row < 0 or curr_row >= len(map_list) or curr_col < 0 or curr_col >= len(map_list[0]):
                    print([start_row, start_col].__str__() + " failed on " + [curr_row, curr_col].__str__() + " with index out of bounds")
                    valid_move = False
                elif map_list[curr_row][curr_col] == 1:
                    print([start_row, start_col].__str__() + " failed on " + [curr_row, curr_col].__str__() + " with a land position")
                    valid_move = False
                move_index += 1
            if move_index == len(move_list):
                print("Made it all the way through with " + [curr_row, curr_col].__str__())
                return
            start_col += 1
        start_row += 1
    return [-1, -1]


# map_name = input("What file would you like to use as a map? ")
map_arr = create_map("seaMap.txt")

# movements_name = input("What file contains the movements of the enemy sub? ")
move_arr = create_movement_list("movements.txt")
# print(len(move_arr))
sub_coords = find_sub_position(map_arr, move_arr)
print("The following coordinates are for the sub: " + sub_coords.__str__())
