import SwiftUI
import shared

struct ContentView: View {
	//let greet = Greeting().greeting()
@State var users = "Loading..."
let sdk = MultiplatformSdk.doInit()


	var body: some View {
		Text("")
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}