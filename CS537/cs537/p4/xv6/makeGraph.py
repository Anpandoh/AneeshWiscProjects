import matplotlib.pyplot as plt

def plot_data(filename):
    # Initialize empty dictionary to store PROC and TICK values
    proc_values = {}

    # Read the data from the file and plot
    with open(filename, "r") as file:
        line_count = 0
        for line in file:
            if "PROC" in line:
                proc = int(line.split("PROC:")[1].split("|")[0].strip())
                if proc == 3:
                    continue
                tick = int(line.split("TICK:")[1].strip())
                if proc in proc_values:
                    proc_values[proc].append(tick)
                else:
                    proc_values[proc] = [tick]

            line_count += 1

    # Sort the TICK values for each PROC
    for proc in proc_values:
        proc_values[proc].sort()

    # Plot the data
    plt.figure(figsize=(10, 6))
    for proc, ticks in proc_values.items():
        y_values = list(range(1, len(ticks) + 1))
        plt.plot(ticks, y_values, label=f"PROC {proc}")

    plt.ylabel("Execution Ticks / Processes")
    plt.xlabel("Ticks")
    plt.legend()
    plt.title(f"{filename[:-4]} Process Execution")

    # Show the plot or save it to a file
    plt.show()
    plt.savefig(f'{filename[:-4]}.png')

plot_data("MLFQdata.txt")
plot_data("RRdata.txt")
