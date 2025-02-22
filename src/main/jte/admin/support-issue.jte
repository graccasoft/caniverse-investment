@param org.springframework.security.web.csrf.CsrfToken csrfToken
@param io.caniverse.investment.model.entity.SupportIssue supportIssue
<!DOCTYPE html>
<html lang="en">
<head>
    @template.partials.header(title = "Support")
</head>
<body
        x-data="{ page: 'support', 'loaded': true, 'darkMode': true, 'stickyMenu': false, 'sidebarToggle': false, 'scrollTop': false }"
>
@template.partials.preloader()
<div class="flex h-screen overflow-hidden">
    @template.admin.aside()
    <div class="relative flex flex-1 flex-col overflow-y-auto overflow-x-hidden">
        @template.partials.admin-profile()
        <main>
            <div class="mx-auto max-w-screen-2xl p-4 md:p-6 2xl:p-10">

                @template.partials.support-issue(supportIssue = supportIssue)

                <div class="rounded-sm border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">

                    <form action="/admin/support/${supportIssue.getId()}/response" method="post">
                        <input type="hidden" name="${csrfToken.getParameterName()}" value="${csrfToken.getToken()}">
                        <input type="hidden" name="supportIssueId" value="${supportIssue.getId()}">
                        <div class="p-6.5">

                            <div class="mb-4.5 flex flex-col gap-6 xl:flex-row">
                                <div class="w-full xl:w-1/2">
                                    <label class="mb-3 block text-sm font-medium text-black dark:text-white">
                                        Message <span class="text-meta-1">*</span>
                                    </label>
                                    <textarea required name="response" placeholder="Enter a message..." type="text"
                                              rows="6"
                                              class="dark:bg-dark-900 w-full rounded-lg border border-gray-300 bg-transparent px-4 py-2.5 text-sm text-gray-800 shadow-theme-xs placeholder:text-gray-400 focus:border-brand-300 focus:outline-none focus:ring focus:ring-brand-500/10 dark:border-gray-700 dark:bg-gray-900 dark:text-white/90 dark:placeholder:text-white/30 dark:focus:border-brand-800"
                                              spellcheck="false"></textarea>
                                </div>

                            </div>
                            <div x-data="{ isOptionSelected: false }"
                                 class="relative z-20 bg-transparent dark:bg-form-input mb-5">
                                <select class="relative z-20 w-full appearance-none rounded border border-stroke bg-transparent px-5 py-3 outline-none transition focus:border-primary active:border-primary dark:border-form-strokedark dark:bg-form-input dark:focus:border-primary"
                                        :class="isOptionSelected &amp;&amp; 'text-black dark:text-white'"
                                        @change="isOptionSelected = true" name="status" required>
                                    <option value="OPEN" class="text-body">
                                        Status
                                    </option>
                                    <option value="OPEN" class="text-body">OPEN</option>
                                    <option value="CLOSED" class="text-body">CLOSED</option>
                                </select>
                                <span class="absolute right-4 top-1/2 z-30 -translate-y-1/2">
                                        <svg class="fill-current" width="24" height="24" viewBox="0 0 24 24" fill="none"
                                             xmlns="http://www.w3.org/2000/svg">
                                          <g opacity="0.8">
                                            <path fill-rule="evenodd" clip-rule="evenodd"
                                                  d="M5.29289 8.29289C5.68342 7.90237 6.31658 7.90237 6.70711 8.29289L12 13.5858L17.2929 8.29289C17.6834 7.90237 18.3166 7.90237 18.7071 8.29289C19.0976 8.68342 19.0976 9.31658 18.7071 9.70711L12.7071 15.7071C12.3166 16.0976 11.6834 16.0976 11.2929 15.7071L5.29289 9.70711C4.90237 9.31658 4.90237 8.68342 5.29289 8.29289Z"
                                                  fill=""></path>
                                          </g>
                                        </svg>
                                    </span>
                            </div>
                            <button class="flex w-full justify-center rounded bg-primary p-3 font-medium text-gray hover:bg-opacity-90">
                                Send Response
                            </button>
                        </div>
                    </form>

                </div>
            </div>
        </main>
    </div>
</div>
@template.partials.footer()
</body>

</html>