package com.lucasdam.githubchanllenge.shared

import com.lucasdam.githubchanllenge.shared.provider.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler

/**
 * @author Lucas Marciano on 05/04/20.
 */

class TestSchedulerProvider(private val testScheduler: TestScheduler) : SchedulerProvider {

    override fun io(): Scheduler = testScheduler

    override fun ui(): Scheduler = testScheduler

    override fun computation(): Scheduler = testScheduler
}
