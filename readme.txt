A collection of Java tools to facilitate the export of all Freshdesk data to a database via a JDBC connection. Data can also be dumped to CSV format.
Data export from Freshdesk is completed via Freshdesk's REST API.

Separate main methods exist for extracting tickets, knowledge base articles, contacts (people), and contacts (companies).

The provided database scripts will create the necessary backend for SQL Server. These scripts provide the bare minimum functionality and datatypes have not been optimized.

All attachements are saved to disk. This includes email signature logos and anything else which was attached to the tickets and emails.

Freshdesk API calls are limited to 1,000 per hour. Your API key will be needed for the "UsernamePasswordCrendentials". The API key is available in the Freshdesk UI,
     in "Profile settings".

The following additional modifications will be needed in the "Extract" classes:

ExtractTickets: A list of ticket numbers is read from a CSV file. The API is then used to extract only these ticket numbers. The CSV file can be generated through Freshdesk's UI. Click "Export" 
                on the Tickets tab in Freshdesk. Choose to export only the "Ticket ID". In ExportTickets.java, change the filepath parameter of the CsvReader reader and point it to your CSV file.
		        When the class is run, the CSV file will be used as a reference. Each ticket number in the file will be extracted and a call to the RESTful web service will be made.
		        Example: If the CSV file contains "1000,1001", then ticket numbers 1000 and 1001 will be exported.
		 
ExtractContacts: No CSV file is needed (but the code can be changed to use a CSV file). Instead, a "for" loop is used to traverse each page of Contacts in Freshdesk. 
                The number of iterations in the "for" loop is currently set to 12. This will need to be set to the number of pages in your environment when all Contacts are listed.
		        Go to FreshDesk > Customers > Contacts (https://yourdomain.freshdesk.com/contacts). Scroll to the bottom of the page to see how many pages of contacts are stored. 
		        Use this number instead of "12".
		  
ExtractCompany: Uses the same methodoloy as ExtractContacts. 

Additionally, the following should be noted.

Notes.java: The Notes class exists due to an error in the Freshdesk REST API (api/v1). The JSON response contains a superfluous set of brackets, which causes
            an array of Note objects to be returned as a single array of Notes. Thus, "notes" is a wrapper which always contains a single object. This
			issue should be resolved in api/v2. 

api/v1: Since these classes are written in V1, other changes may be needed. Details can be found here: http://developer.freshdesk.com/api/
      
