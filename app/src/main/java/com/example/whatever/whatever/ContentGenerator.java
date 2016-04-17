package com.example.whatever.whatever;

/**
 * Created by msi on 2016/4/17.
 */
public class ContentGenerator {
    public static String[] generateContent(int counter)
    {
        String[] content = new String[4];
        if(counter == 0)
        {
            content[0] = "Bigpack Café";
            content[1] = "3/F, 76A Fa Yuen Street, Mong Kok";
            content[2] = "German";
            content[3] = "201-400";
        }
        else if(counter ==2)
        {
            content[0] = "Lucky Indonesian Restaurant";
            content[1] = "G/F, 46 Tung Ming Street, Kwun Tong";
            content[2] = "Indonesian";
            content[3] = "1-100";
        }
        else
        {
            content[0] = "K Lok Spicy Chicken Hotpot";
            content[1] = "Shop 4, G/F, Quality Tower, 29-31 Hillwood Road, Tsim Sha Tsui";
            content[2] = "Sichuan";
            content[3] = "201-400";
        }
        return content;
    }
}
