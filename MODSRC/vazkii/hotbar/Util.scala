package vazkii.hotbar

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.client.Minecraft
import net.minecraft.client.multiplayer.PlayerControllerMP
import net.minecraft.client.entity.EntityClientPlayerMP

object Util {

	def getPlayer: EntityClientPlayerMP = Minecraft.getMinecraft().thePlayer

	def getController: PlayerControllerMP = Minecraft.getMinecraft().playerController

	private def getWindow: Int = getPlayer.inventoryContainer.windowId

	def swapHotbar(up : Boolean) = {
		if(up) {
			swapHotbarDo({_ - 27}, 36, 44);
			swapHotbarDo({_ + 9}, 18, 26);
			swapHotbarDo({_ - 18}, 36, 44);
		} else {
			swapHotbarDo({_ - 9}, 18, 26)
			swapHotbarDo({_ - 18}, 36, 44)
			swapHotbarDo({_ - 9}, 27, 35)
		}
	}

	private def swapHotbarDo(f : (Int => Int), start : Int, end : Int) {
		for(i <- start to end)
			swapItems(i, f(i))
	}
	
	private def swapItems(start : Int, end : Int) = {
		val controller : PlayerControllerMP = getController
		val window : Int = getWindow
		
		val startHasSlot : Boolean = getPlayer.inventory.getStackInSlot(getPlayer.inventoryContainer.getSlot(start).getSlotIndex()) != null
		val endHasSlot : Boolean = getPlayer.inventory.getStackInSlot(getPlayer.inventoryContainer.getSlot(end).getSlotIndex()) != null
		
		var slots : Array[Int] = Array()
		
		if(startHasSlot)
			slots = slots :+ start
			
		if(startHasSlot || endHasSlot)
			slots = slots :+ end
		
		if(endHasSlot)
			slots = slots :+ start

		click(controller, slots, window)
	}

	private def click(controller : PlayerControllerMP, slots : Array[Int], window : Int) = {
		for(i <- slots)
			controller.windowClick(window, i, 0, 0, getPlayer)
	}
}