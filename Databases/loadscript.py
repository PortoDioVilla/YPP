'''
This is a quick script I wrote to import an excel spreadhsheet's values into a new sql database.
One fundamental assumption of this spreadhsheet is that each relation is described in a roundrobin way.
This means that Island A and Island B both have the same important number, their distance, and the spreadsheet has that data.
This is a redundancy we'd like to eliminate.
'''

def algorithm(Sheet sheet, Table table)
	'''takes in a sheet, and writes the upper-triangular entries to a mysql database's table.
	sheet: the sheet with the relevant data
	table: the table to which the data must be written
	'''
	
	for i in range(1, sheet.max_row)
		for j in range(i, sheet.max_col)
			#write in the data
			# INSERT INTO table (first, second, distance)
			# values(sheet.cell(i,1), sheet.cell(1,j), sheet.cell(i,j)
			
	