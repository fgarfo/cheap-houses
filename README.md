# cheap-houses
**General Description:**
This program restructures data of market houses from a file and uses Java's swing library to display the houses in a plot based on which ones fall under the price range the user inputs.

**In-depth Description:**
This program reads data from a CSV file that includes the addresses, prices, and latitude/longitude coordinates of houses in a county. It reads the address, price, and coordinates and stores them in a data structure(Map). 

Houses that cost less than $125000, for example, are identified and their locations are plotted as small filled circles in a window to visualize their density and relative locations. The addresses and prices of these houses are also saved in a file called “cheaphouses.txt” which updates based on the input provided. 

The program has a simple graphical user interface(GUI) that allows a user to specify the CSV file to be read and the cutoff price for the visualization.

Here's a visual representation of a CSV file called "houses" given two different cutoff prices:
![Alt text]((https://user-images.githubusercontent.com/100949219/246949924-eb2d8dbc-9481-4fe8-b8c3-8b34c71116df.png))
![Alt text](image link)
![Screen Shot 2023-06-19 at 4 02 50 PM](https://github.com/fgarfo/cheap-houses/assets/100949219/eb2d8dbc-9481-4fe8-b8c3-8b34c71116df)
![Screen Shot 2023-06-19 at 4 03 25 PM](https://github.com/fgarfo/cheap-houses/assets/100949219/27feaeb4-e77f-4b47-9a52-d16009d90ef2)

