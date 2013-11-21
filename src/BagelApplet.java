//Michael lindvall
//pg 260 #8.4 bagel List box

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import java.text.*;
public class BagelApplet extends Applet implements ItemListener
{
    //Declear the prompts
    Label lblCount = new Label("Count of Bagel Type");
    Label lblError = new Label("                              ");

    List lstBagels = new List(10);
    TextField txtBagel = new TextField(10);
    Choice chcBagel = new Choice();
    Label lblDisplayCount = new Label("                ");

    public void init()
    {
      DesignLayout();
      FillList();
      txtBagel.requestFocus();

      chcBagel.addItemListener(this);

    }

    public void DesignLayout()
   {
     //Set layout manager
     GridBagLayout xyz = new GridBagLayout();
     setLayout(xyz);
     GridBagConstraints constraints = new GridBagConstraints();
     setLayout(xyz);
     constraints.insets = new Insets(5, 5, 5, 5);

     //Row 1 Label
     constraints.gridx = 0;
     constraints.gridy = 0;
     constraints.gridwidth = 1;
     constraints.anchor = GridBagConstraints.EAST;
     xyz.setConstraints(lstBagels, constraints);
     add(lstBagels);


     //Row 2 Label
     constraints.gridx = 0;
     constraints.gridy = 1;
     constraints.gridwidth = 1;
     constraints.anchor = GridBagConstraints.EAST;
     xyz.setConstraints(txtBagel, constraints);
     add(txtBagel);



     //Row 3 Label
     constraints.gridx = 0;
     constraints.gridy = 2;
     constraints.gridwidth = 1;
     constraints.anchor = GridBagConstraints.EAST;
     xyz.setConstraints(chcBagel, constraints);
     add(chcBagel);

     //Row 4 Label
     constraints.gridx = 0;
     constraints.gridy = 3;
     constraints.gridwidth = 1;
     constraints.anchor = GridBagConstraints.EAST;
     xyz.setConstraints(lblCount, constraints);
     add(lblCount);

     //Row 4 Label
    constraints.gridx = 1;
    constraints.gridy = 3;
    constraints.gridwidth = 1;
    constraints.anchor = GridBagConstraints.EAST;
    xyz.setConstraints(lblDisplayCount, constraints);
    add(lblDisplayCount);

    //Row 4 Label
    constraints.gridx = 0;
    constraints.gridy = 4;
    constraints.gridwidth = 1;
    constraints.anchor = GridBagConstraints.EAST;
    xyz.setConstraints(lblError, constraints);
    lblError.setBackground(Color.red);
    add(lblError);
   }


   public void FillList()
   {
     lstBagels.add("Plain");
     lstBagels.add("Egg");
     lstBagels.add("Rye");
     lstBagels.add("Salt");
     lstBagels.add("Poppy Seed");
     lstBagels.add("Sesame Seed");
     lstBagels.add("Banana Nut");
     lstBagels.add("Blueberry");


     //Chose list
     chcBagel.add("Add Bagel Type");
     chcBagel.add("Remove Bagel Type");
     chcBagel.add("Clear Bagel List");
     chcBagel.add("Display Bagel Count");

   }

   public void itemStateChanged(ItemEvent event)
   {
     //Determine which demand is selected from the drop down list
     int intCommand = chcBagel.getSelectedIndex();

     switch (intCommand)
     {
        case 0:
            addBagel();
            break;
        case 1:
            removeBagel();
            break;
        case 2:
            clearBagel();
            break;
        case 3:
            countBagel();
            break;
     }
   }

   public void addBagel()
   {
     //ad a bagel type to the list
     if(txtBagel.getText().equals(""))
     {
       lblError.setText("Enter a bagel type");
       showStatus("Enter a Bagel Type");
       txtBagel.requestFocus();
     }
     else
     {
         String strBagel = txtBagel.getText();
         //Check for duplication
         boolean blnFindDuplicate = FindDuplicate(strBagel);
         if(blnFindDuplicate)
         {
           lblError.setForeground(Color.red);
           lblError.setText("Duplicate Bagel already exist");
           showStatus("Bagel Alreay in List");
           txtBagel.selectAll();
           txtBagel.requestFocus();
         }
         else
         {
             lstBagels.add(strBagel);
             showStatus("Bagel " + strBagel + " added to list");
             lblError.setText("");
             txtBagel.setText("");
             txtBagel.requestFocus();

         }
     }
   }

   public boolean FindDuplicate(String strFind)
   {
     //look for a match between the text box and list elements
     boolean blnItemFound = false;
     int intItemIndex = 0;
     int intNumberItems = lstBagels.getItemCount();
     String strListElement;

     while(!blnItemFound && intItemIndex < intNumberItems)
     {
       strListElement = lstBagels.getItem(intItemIndex++);
       if(strFind.equalsIgnoreCase(strListElement))
       {
         blnItemFound = true;
       }
     }
     return blnItemFound;
   }


   public void removeBagel()
   {
     //Remove selected bagel from this list of bagels
     int intIndex = lstBagels.getSelectedIndex();
     if (intIndex == -1)
     {
       lblError.setForeground(Color.RED);
       lblError.setText("Please pick a bagel type to remove");
       showStatus("No bagel selected");
     }
     else
     {
         String strRemoveItem = ""+lstBagels.getSelectedItem();
         lstBagels.remove(intIndex);
         lblError.setText("");
         showStatus("Item Removed: " + strRemoveItem);
     }
   }


   public void clearBagel()
   {
     lstBagels.removeAll();
   }

   public void countBagel()
   {
     String strCount = ""+lstBagels.getItemCount();
     lblDisplayCount.setText(strCount);
   }
}































