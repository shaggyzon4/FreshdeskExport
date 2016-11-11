USE [freshdesk]
GO

/****** Object:  Table [dbo].[notes]    Script Date: 11/11/2016 9:19:55 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[notes](
	[id] [varchar](50) NOT NULL,
	[ticketnumber] [int] NULL,
	[ticketid] [nvarchar](max) NULL,
	[incoming] [varchar](10) NULL,
	[body] [nvarchar](max) NULL,
	[updated_at] [nvarchar](50) NULL,
	[source] [nvarchar](max) NULL,
	[body_html] [nvarchar](max) NULL,
	[created_at] [nvarchar](50) NULL,
	[user_id] [nvarchar](max) NULL,
	[deleted] [varchar](10) NULL,
	[support_email] [nvarchar](max) NULL,
	[isPrivate] [nvarchar](50) NULL,
 CONSTRAINT [PK_notes] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

