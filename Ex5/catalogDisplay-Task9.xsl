<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="catalog">
        <!-- Извеждане на инфо за каталога в елемента data -->
        <xsl:element name="catalog">
            <!-- Итериране по елементите на каталога -->
            <xsl:for-each select="cd">
                <xsl:element name="cd">
                    <xsl:element name="title">
                        <xsl:value-of select="title"/>
                    </xsl:element>
                    <xsl:element name="tracklist">
                        <!-- Итериране по елементите на tracklist -->
                        <xsl:for-each select="tracklist/track">
                            <xsl:element name="track">
                                <xsl:value-of select="text()"/>
                            </xsl:element>
                        </xsl:for-each>
                    </xsl:element>
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>