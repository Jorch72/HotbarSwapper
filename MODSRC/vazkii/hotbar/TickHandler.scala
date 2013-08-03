package vazkii.hotbar

import java.util.EnumSet
import cpw.mods.fml.common.ITickHandler
import cpw.mods.fml.common.TickType
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.GuiScreen
import org.lwjgl.input.Mouse
import net.minecraft.client.Minecraft

class TickHandler extends ITickHandler {

	var slot : Int = -1
	var wheel : Int = 0
	
	@Override
	def tickStart(tickType : EnumSet[TickType], data : Object*) : Unit = {
		if(Minecraft.getMinecraft().currentScreen == null) {
			val ctrl : Boolean = GuiScreen.isCtrlKeyDown
			
			if(ctrl) {
				wheel = Mouse.getDWheel
				slot = Util.getPlayer.inventory.currentItem
			}
		} else {
			slot = -1
			wheel = 0
		}
	}
	
	@Override
	def tickEnd(tickType : EnumSet[TickType], tickData : Object*) : Unit = {
		if(GuiScreen.isCtrlKeyDown) {
			if(slot >= 0 && slot != Util.getPlayer.inventory.currentItem) {
				Util.getPlayer.inventory.currentItem = slot
				Util.getController.updateController();
				
				Util.swapHotbar(Mouse.getDWheel - wheel < 0)
				
				slot = -1
			}
		} else slot = -1
	}
	
	@Override
	def ticks : EnumSet[TickType] = EnumSet.of(TickType.CLIENT)
	
	@Override
	def getLabel : String = "HotbarSwapper"
}