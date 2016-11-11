USE [freshdesk]
GO

/****** Object:  Table [dbo].[tickets]    Script Date: 11/11/2016 9:20:12 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[tickets](
	[ticketnumber] [int] NOT NULL,
	[ticketid] [nvarchar](max) NULL,
	[subject] [nvarchar](max) NULL,
	[description] [nvarchar](max) NULL,
	[descriptionhtml] [nvarchar](max) NULL,
	[creationdate] [nvarchar](max) NULL,
	[userid] [nvarchar](max) NULL,
	[username] [nvarchar](max) NULL,
	[hasattachments] [nvarchar](max) NULL,
	[ownerid] [nvarchar](max) NULL,
	[ccemailaddys] [nvarchar](max) NULL,
 CONSTRAINT [PK_tickets] PRIMARY KEY CLUSTERED 
(
	[ticketnumber] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

