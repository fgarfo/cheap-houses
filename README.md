# cheap-houses
**General Description:**
This program restructures data of market houses from a file and uses Java's swing library to display the houses in a plot based on which ones fall under the price range the user inputs.

**In-depth Description:**
This program reads data from a CSV file that includes the addresses, prices, and latitude/longitude coordinates of houses in a county. It reads the address, price, and coordinates and stores them in a data structure(Map). 

Houses that cost less than $125000, for example, are identified and their locations are plotted as small filled circles in a window to visualize their density and relative locations. The addresses and prices of these houses are also saved in a file called “cheaphouses.txt” which updates based on the input provided. 

The program has a simple graphical user interface(GUI) that allows a user to specify the CSV file to be read and the cutoff price for the visualization.

Here's a visual representation of a CSV file called "houses" given two different cutoff prices:
![Screen Shot 2023-06-19 at 8 04 03 PM](https://github.com/fgarfo/cheap-houses/assets/100949219/988f0891-b5ad-4bcb-adf9-668b36e209af)
![Screen Shot 2023-06-19 at 8 04 17 PM](https://github.com/fgarfo/cheap-houses/assets/100949219/192611a4-f804-4e22-823d-0ca92c469b98)

