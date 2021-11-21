<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head></head>
            <body>
                <xsl:for-each select="catalog/cd">
                    <ul>
                        <!-- Извикване на темплейтите в съответния list item -->
                        <li>
                            <xsl:call-template name="year"/>
                        </li>
                        <li>
                            <xsl:call-template name="title"/>
                        </li>
                        <li>
                            <xsl:call-template name="artist"/>
                        </li>
                    </ul>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
    <!-- Дефиниране на шаблоните -->
    <xsl:template name="year">
        <xsl:value-of select="year"/>
    </xsl:template>
    <xsl:template name="title">
        <xsl:value-of select="title"/>
    </xsl:template>
    <xsl:template name="artist">
        <xsl:value-of select="artist"/>
    </xsl:template>
</xsl:stylesheet>