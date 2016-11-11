USE [freshdesk]
GO

/****** Object:  Table [dbo].[contacts]    Script Date: 11/11/2016 9:19:45 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[contacts](
	[id] [nvarchar](50) NOT NULL,
	[company_id] [nvarchar](50) NULL,
	[name] [nvarchar](100) NULL,
	[email] [nvarchar](100) NULL,
	[phone] [nvarchar](50) NULL,
	[description] [nvarchar](max) NULL,
	[time_zone] [nvarchar](50) NULL,
	[created_at] [nvarchar](50) NULL,
	[helpdesk_agent] [nvarchar](50) NULL,
 CONSTRAINT [PK_users] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

