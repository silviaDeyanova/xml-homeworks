<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head></head>
            <body>
                <xsl:for-each select="catalog/cd">
                    <ul>
                        <!-- Извикване на темплейтите с параметри в съответния list item -->
                        <li>
                            <xsl:call-template name="year">
                                <xsl:with-param name="yearValue" select="year"/>
                            </xsl:call-template>
                        </li>
                        <li>
                            <xsl:call-template name="title">
                                <xsl:with-param name="titleValue" select="title"/>
                            </xsl:call-template>
                        </li>
                        <li>
                            <xsl:call-template name="artist">
                                <xsl:with-param name="artistValue" select="artist"/>
                            </xsl:call-template>
                        </li>
                    </ul>
                </xsl:for-each>
            </body>
        </html>
    </xsl:template>
    <!-- Дефиниране на шаблоните с параметри -->
    <xsl:template name="year">
        <xsl:param name="yearValue"/>
        <xsl:value-of select="$yearValue"/>
    </xsl:template>
    <xsl:template name="title">
        <xsl:param name="titleValue"/>
        <xsl:value-of select="$titleValue"/>
    </xsl:template>
    <xsl:template name="artist">
        <xsl:param name="artistValue"/>
        <xsl:value-of select="$artistValue"/>
    </xsl:template>
</xsl:stylesheet>