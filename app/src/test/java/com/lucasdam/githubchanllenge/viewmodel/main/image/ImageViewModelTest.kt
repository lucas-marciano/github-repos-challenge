package com.lucasdam.githubchanllenge.viewmodel.main.image

import com.lucasdam.githubchanllenge.shared.model.view.Repository
import com.lucasdam.githubchanllenge.ui.main.image.ImageContract
import com.lucasdam.githubchanllenge.ui.main.image.ImageViewModelImpl
import com.lucasdam.githubchanllenge.shared.ViewModelTest
import com.jraska.livedata.TestObserver
import io.mockk.confirmVerified
import io.mockk.mockk
import org.junit.Test

/**
 * @author Lucas Marciano on 03/01/21.
 */
class ImageViewModelTest : ViewModelTest<ImageContract.ViewModel>() {

    override lateinit var viewModel: ImageContract.ViewModel

    private val interactor = mockk<ImageContract.Interactor>()
    private val onGetRepositoryUrlTest: TestObserver<String> = TestObserver.create()

    override fun initialize() {
        viewModel = ImageViewModelImpl(interactor)
    }

    @Test
    fun `get url successfully`() {
        viewModel.output.onGetRepositoryUrl.observeForever(onGetRepositoryUrlTest)

        viewModel.input.loadUrl(Repository("", "url"))

        onGetRepositoryUrlTest.assertValue("url")

        confirmVerified(interactor)
    }
}
