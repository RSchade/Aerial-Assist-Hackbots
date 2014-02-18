package com.fpsrobotics;

public class IntegerPair
{

    private int fieldTwo;
    private int fieldOne;

    public IntegerPair(int fieldOne, int fieldTwo)
    {
        this.fieldOne = fieldOne;
        this.fieldTwo = fieldTwo;
    }

    public IntegerPair()
    {
        this(0, 0);
    }

    public int getFieldOne()
    {
        return fieldOne;
    }

    public int getFieldTwo()
    {
        return fieldTwo;
    }

    public void setFieldOne(int fieldOne)
    {
        this.fieldOne = fieldOne;
    }

    public void setFieldTwo(int fieldTwo)
    {
        this.fieldTwo = fieldTwo;
    }
    public int hashCode()
    {
        int hash = 7;
        hash = 73 * hash + this.fieldTwo;
        hash = 73 * hash + this.fieldOne;
        return hash;
    }

    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final IntegerPair other = (IntegerPair) obj;
        if (this.fieldTwo != other.fieldTwo)
        {
            return false;
        }
        if (this.fieldOne != other.fieldOne)
        {
            return false;
        }
        return true;
    }
}
