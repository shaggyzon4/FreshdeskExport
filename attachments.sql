USE [freshdesk]
GO

/****** Object:  Table [dbo].[attachments]    Script Date: 11/11/2016 9:18:24 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[attachments](
	[id] [nvarchar](max) NOT NULL,
	[ticketid] [nvarchar](max) NULL,
	[ticket_display_id] [int] NULL,
	[content_file_name] [nvarchar](max) NULL,
	[content_content_type] [nvarchar](max) NULL,
	[attachment_url] [nvarchar](max) NULL,
	[updated_at] [nvarchar](max) NULL,
	[created_at] [nvarchar](max) NULL,
	[content_file_size] [nvarchar](max) NULL,
	[note_id] [nvarchar](max) NULL
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO

