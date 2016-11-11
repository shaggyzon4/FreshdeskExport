USE [freshdesk]
GO

/****** Object:  Table [dbo].[companies]    Script Date: 11/11/2016 9:19:29 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[companies](
	[id] [nvarchar](50) NOT NULL,
	[description] [nvarchar](max) NULL,
	[name] [nvarchar](max) NULL,
	[domains] [nvarchar](max) NULL,
	[created_at] [nvarchar](50) NULL,
	[note] [nvarchar](max) NULL,
 CONSTRAINT [PK_companies] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

